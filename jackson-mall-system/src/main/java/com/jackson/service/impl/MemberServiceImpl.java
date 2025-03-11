package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.jackson.constant.EmailConstant;
import com.jackson.constant.MemberConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.dto.MemberLoginDTO;
import com.jackson.dto.MemberSendCodeDTO;
import com.jackson.entity.ShopMember;
import com.jackson.exception.AccountLockException;
import com.jackson.exception.CodeErrorException;
import com.jackson.exception.MemberNotFoundException;
import com.jackson.exception.PasswordErrorException;
import com.jackson.repository.MemberRepository;
import com.jackson.result.Result;
import com.jackson.service.MemberService;
import com.jackson.utils.JwtUtils;
import com.jackson.utils.MailUtils;
import com.jackson.vo.MemberLoginVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MailUtils mailUtils;

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
            // 手机号登录
            shopMember = memberRepository.findByNickname(memberLoginDTO.getNickname());
            // 判断该用户是否存在
            if (shopMember == null) {
                // 不存在,返回错误信息
                throw new MemberNotFoundException(MemberConstant.MEMBER_NOT_FOUND);
            }
            // 手机号搭配密码进行登录
            if (!memberLoginDTO.getPassword().equals(shopMember.getPassword())) {
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
}
