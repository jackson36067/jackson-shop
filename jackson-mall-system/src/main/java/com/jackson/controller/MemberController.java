package com.jackson.controller;

import com.jackson.dto.MemberLoginDTO;
import com.jackson.dto.MemberSendCodeDTO;
import com.jackson.result.Result;
import com.jackson.service.MemberService;
import com.jackson.vo.MemberLoginVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param memberSendCodeDTO
     */
    @PostMapping("/code")
    public void sendCode(@RequestBody MemberSendCodeDTO memberSendCodeDTO) {
        memberService.sendCode(memberSendCodeDTO);
    }
}
