package com.jackson.controller;

import com.jackson.dto.MemberLoginDTO;
import com.jackson.dto.MemberSendCodeDTO;
import com.jackson.result.Result;
import com.jackson.service.MemberService;
import com.jackson.vo.MemberLoginVO;
import com.jackson.vo.MemberVO;
import com.jackson.dto.UpdateMemberDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 用户登录
     *
     * @param memberLoginDTO 用户登录参数
     */
    @PostMapping("/login")
    public Result<MemberLoginVO> memberLogin(@RequestBody MemberLoginDTO memberLoginDTO) {
        return memberService.memberLogin(memberLoginDTO);
    }

    /**
     * 发送验证码
     *
     * @param memberSendCodeDTO
     */
    @PostMapping("/code")
    public void sendCode(@RequestBody MemberSendCodeDTO memberSendCodeDTO) {
        memberService.sendCode(memberSendCodeDTO);
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result<MemberVO> getMemberInfo() {
        return memberService.getMemberInfo();
    }

    /**
     * 上传图片接口
     *
     * @param file 图片文件
     * @return
     */
    @PostMapping("/upload")
    public Result<String> uploadImage(MultipartFile file) {
        return memberService.uploadImage(file);
    }

    /**
     * 更新用户接口
     * @param updateMemberDTO 更新用户数据参数
     */
    @PutMapping("/update")
    public void updateMember(@RequestBody UpdateMemberDTO updateMemberDTO) {
        memberService.updateMember(updateMemberDTO);
    }
}
