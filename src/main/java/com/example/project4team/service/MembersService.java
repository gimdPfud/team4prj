package com.example.project4team.service;

import com.example.project4team.dto.MembersDTO;

public interface MembersService {
    public MembersDTO insertMembers(MembersDTO membersDTO);
    public MembersDTO readMembers(MembersDTO membersDTO);
    public Long modifyMembers(MembersDTO membersDTO);
}
