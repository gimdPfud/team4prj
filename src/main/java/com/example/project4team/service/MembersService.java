package com.example.project4team.service;

import com.example.project4team.dto.MembersDTO;

public interface MembersService {
    /*회원등록*/
    public String insertMembers(MembersDTO membersDTO);

    /*회원상세보기*/
    public MembersDTO readMembers(String email);

    /*회원정보수정*/
    public Long modifyMembers(MembersDTO membersDTO);

    /*비밀번호변경*/
    public boolean changePassword(MembersDTO membersDTO);
}
