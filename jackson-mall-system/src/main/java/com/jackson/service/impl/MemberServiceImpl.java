package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.jackson.constant.EmailConstant;
import com.jackson.constant.MemberConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.*;
import com.jackson.entity.ShopMember;
import com.jackson.exception.*;
import com.jackson.repository.MemberRepository;
import com.jackson.result.Result;
import com.jackson.service.MemberService;
import com.jackson.utils.AliOssUtils;
import com.jackson.utils.JwtUtils;
import com.jackson.utils.MailUtils;
import com.jackson.vo.MemberLoginVO;
import com.jackson.vo.MemberVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MailUtils mailUtils;
    @Resource
    private AliOssUtils aliOssUtils;

    /**
     * 用户登录
     *
     * @param memberLoginDTO 用户登录参数
     */
    public Result<MemberLoginVO> memberLogin(MemberLoginDTO memberLoginDTO) {
        ShopMember shopMember;
        // 判断用户谁用什么方式登录
        if (StringUtil.isNullOrEmpty(memberLoginDTO.getNickname())) {
            // 邮箱方式登录
            shopMember = memberRepository.findByEmail(memberLoginDTO.getEmail());
            // 判断该用户是否存在
            if (shopMember == null) {
                // 不存在,返回错误信息
                throw new MemberNotFoundException(MemberConstant.MEMBER_NOT_FOUND);
            }
            // 校验验证码是否正确
            String code = stringRedisTemplate.opsForValue().get(RedisConstant.SHOP_EMAIL_CODE_PREFIX + shopMember.getEmail());
            if (!memberLoginDTO.getEmailCode().equals(code)) {
                // 不正确返回报错信息
                throw new CodeErrorException(MemberConstant.CODE_ERROR);
            }
        } else {
            // 用户名登录
            shopMember = memberRepository.findByNickname(memberLoginDTO.getNickname());
            // 判断该用户是否存在
            if (shopMember == null) {
                // 不存在,返回错误信息
                throw new MemberNotFoundException(MemberConstant.MEMBER_NOT_FOUND);
            }
            // 手机号搭配密码进行登录
            String md5Password = DigestUtils.md5DigestAsHex(memberLoginDTO.getPassword().trim().getBytes());
            if (!md5Password.equals(shopMember.getPassword())) {
                throw new PasswordErrorException(MemberConstant.MEMBER_PASSWORD_ERROR);
            }
        }
        // 判断用户账号是否被冻结
        if (shopMember.getStatus() == 1) {
            // 用户账号被冻结
            throw new AccountLockException(MemberConstant.MEMBER_ACCOUNT_LOCK);
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(MemberConstant.member_id, shopMember.getId());
        String token = JwtUtils.genJwt(claims);
        MemberLoginVO memberLoginVO = BeanUtil.copyProperties(shopMember, MemberLoginVO.class);
        memberLoginVO.setToken(token);
        return Result.success(memberLoginVO);
    }

    /**
     * 发送验证码
     *
     * @param memberSendCodeDTO
     */
    public void sendCode(MemberSendCodeDTO memberSendCodeDTO) {
        // 使用糊涂包生成六位数的验证码
        String code = RandomUtil.randomNumbers(6);
        String email = memberSendCodeDTO.getEmail();
        // 发送验证码到指定邮箱
        mailUtils.sendMessage(email, EmailConstant.EMAIL_SUBJECT, code);
        // 将验证码保存到Redis中,其中key以email进行区分,并设置过期时间为2分钟
        stringRedisTemplate.opsForValue().set(RedisConstant.SHOP_EMAIL_CODE_PREFIX + email, code, RedisConstant.CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    public Result<MemberVO> getMemberInfo() {
        Long memberId = BaseContext.getCurrentId();
        ShopMember shopMember = memberRepository.findById(memberId).get();
        MemberVO memberVO = BeanUtil.copyProperties(shopMember, MemberVO.class);
        return Result.success(memberVO);
    }

    /**
     * 上传图片接口
     *
     * @param file 图片文件
     * @return
     */
    public Result<String> uploadImage(MultipartFile file) {
        // 调用ali-util上传图片并且返回图片地址
        String avatarUrl = aliOssUtils.upload(file);
        return Result.success(avatarUrl);
    }

    /**
     * 更新用户接口
     *
     * @param updateMemberDTO 更新用户数据参数
     */
    public void updateMember(UpdateMemberDTO updateMemberDTO) {
        ShopMember shopMember = memberRepository.findById(updateMemberDTO.getId()).get();
        // 判断是否需要更新用户名
        if (!StringUtil.isNullOrEmpty(updateMemberDTO.getNickname()) && !updateMemberDTO.getNickname().equals(shopMember.getNickname())) {
            shopMember.setNickname(updateMemberDTO.getNickname());
        }
        // 判读是否需要更新头像
        if (!StringUtil.isNullOrEmpty(updateMemberDTO.getAvatar()) && !updateMemberDTO.getAvatar().equals(shopMember.getAvatar())) {
            shopMember.setAvatar(updateMemberDTO.getAvatar());
        }
        // 判断是否需要修改性别
        if (updateMemberDTO.getGender() != null && !Objects.equals(updateMemberDTO.getGender(), shopMember.getGender())) {
            shopMember.setGender(updateMemberDTO.getGender());
        }
        // 判断是否需要修改生日
        if (updateMemberDTO.getBirthday() != null && !Objects.equals(updateMemberDTO.getBirthday(), shopMember.getBirthday())) {
            shopMember.setBirthday(updateMemberDTO.getBirthday());
        }
        // 判断是否需要修改手机号
        if (!StringUtil.isNullOrEmpty(updateMemberDTO.getMobile()) && !updateMemberDTO.getMobile().equals(shopMember.getMobile())) {
            shopMember.setMobile(updateMemberDTO.getMobile());
        }
        // 判断是否需要修改邮箱
        if (!StringUtil.isNullOrEmpty(updateMemberDTO.getEmail()) && !updateMemberDTO.getEmail().equals(shopMember.getEmail())) {
            shopMember.setEmail(updateMemberDTO.getEmail());
        }
        memberRepository.saveAndFlush(shopMember);
    }

    /**
     * 用户退出登录
     */
    public void memberLogout() {
        BaseContext.removeCurrentId();
    }

    /**
     * 修改用户密码
     *
     * @param updatePasswordDTO 接收用户新旧密码
     */
    public void updateMemberPassword(UpdatePasswordDTO updatePasswordDTO) {
        ShopMember shopMember = memberRepository.findById(BaseContext.getCurrentId()).get();
        // 比较旧密码是否正取
        String password = DigestUtils.md5DigestAsHex(updatePasswordDTO.getPassword().getBytes());
        if (!password.equals(shopMember.getPassword())) {
            throw new PasswordDifferentException(MemberConstant.PASSWORD_DIFFERENT);
        }
        shopMember.setPassword(DigestUtils.md5DigestAsHex(updatePasswordDTO.getPassword().getBytes()));
        memberRepository.saveAndFlush(shopMember);
    }

    /**
     * 更改用户邮箱
     *
     * @param updateEmailDTO
     */
    public void updateMemberEmailDTO(UpdateEmailDTO updateEmailDTO) {
        ShopMember shopMember = memberRepository.findByEmail(updateEmailDTO.getNewEmail());
        if (shopMember != null) {
            throw new EmailExistException(MemberConstant.EMAIL_EXIST);
        }
        String code = stringRedisTemplate.opsForValue().get(RedisConstant.SHOP_EMAIL_CODE_PREFIX + updateEmailDTO.getNewEmail());
        if (code != null && !code.equals(updateEmailDTO.getCode())) {
            throw new CodeErrorException(MemberConstant.CODE_ERROR);
        }
        shopMember = memberRepository.findById(BaseContext.getCurrentId()).get();
        shopMember.setEmail(updateEmailDTO.getNewEmail());
        memberRepository.saveAndFlush(shopMember);
    }
}
