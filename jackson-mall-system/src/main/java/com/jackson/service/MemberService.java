package com.jackson.service;

import com.jackson.dto.MemberLoginDTO;
import com.jackson.dto.MemberSendCodeDTO;
import com.jackson.result.Result;
import com.jackson.vo.MemberLoginVO;

public interface MemberService {
    Result<MemberLoginVO> memberLogin(MemberLoginDTO memberLoginDTO);

    void sendCode(MemberSendCodeDTO memberSendCodeDTO);
}
