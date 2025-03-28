package com.jackson.service;

import com.jackson.dto.MemberLoginDTO;
import com.jackson.dto.MemberSendCodeDTO;
import com.jackson.dto.UpdateMemberDTO;
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
}
