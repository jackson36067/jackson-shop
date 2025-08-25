package com.jackson.service;

import com.jackson.dto.*;
import com.jackson.result.Result;
import com.jackson.vo.MemberLoginVO;
import com.jackson.vo.MemberVO;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
    Result<MemberLoginVO> memberLogin(MemberLoginDTO memberLoginDTO);

    void sendCode(MemberSendCodeDTO memberSendCodeDTO);

    Result<MemberVO> getMemberInfo();

    Result<String> uploadImage(MultipartFile file);

    void updateMember(UpdateMemberDTO updateMemberDTO);

    void memberLogout();

    void updateMemberPassword(UpdatePasswordDTO updatePasswordDTO);

    void updateMemberEmailDTO(UpdateEmailDTO updateEmailDTO);

    void register(MemberRegisterDTO memberRegisterDTO);
}
