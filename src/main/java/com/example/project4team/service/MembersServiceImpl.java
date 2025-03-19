package com.example.project4team.service;

import com.example.project4team.constant.Role;
import com.example.project4team.dto.MembersDTO;
import com.example.project4team.entity.Members;
import com.example.project4team.repository.MembersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MembersServiceImpl implements MembersService, UserDetailsService {
    private final MembersRepository membersRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;

    @Override
    public String insertMembers(MembersDTO membersDTO) {
        Members members = membersRepository.findByEmail(membersDTO.getEmail());
        if(members!=null){
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
        members = modelMapper.map(membersDTO, Members.class);
        members.setPassword(passwordEncoder.encode(membersDTO.getPassword()));
        membersRepository.save(members);
        return members.getName();
    }

    @Override
    public MembersDTO readMembers(String email) {
        Members members = membersRepository.findByEmail(email);
        if(members==null){
            return null;
        }
        MembersDTO membersDTO = modelMapper.map(members, MembersDTO.class);
        return membersDTO;
    }

    @Override
    public Long modifyMembers(MembersDTO membersDTO) {
        log.info("수정 들어온 값 : "+membersDTO);
        Members members = membersRepository.findById(membersDTO.getMembersNum())
                .orElseThrow(EntityNotFoundException::new);
        members.setName(membersDTO.getName());
        return members.getMembersNum();
    }

    @Override
    public boolean changePassword(MembersDTO membersDTO) {
        /*새 비밀번호끼리 비교*/
        if(membersDTO.getNewPassword1().equals(membersDTO.getNewPassword2())){
            return false;
        }

        /*이메일 확인*/
        Members members = membersRepository.findByEmail(membersDTO.getEmail());
        if(members==null){
            return false;
        }

        /*기존비밀번호 확인*/
        boolean matches = passwordEncoder.matches(membersDTO.getPassword(), members.getPassword());
        if(matches){
            members.setPassword(passwordEncoder.encode(membersDTO.getNewPassword1()));
            return true;
        }else{
            log.info("기존 비밀번호 불일치");
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Members members = membersRepository.findByEmail(email);
        log.info("유저 찾아오기 : "+members);
        if(members==null){
            log.info("회원 못찾음.");
            throw new AuthenticationServiceException("가입된 회원이 아닙니다.");
        }

        /*시큐리티에서 role이 있으니.. hasrole로 정할거임..*/
        String role = null;
        if(members.getRole().name().equals("ADMIN")){
            role= Role.ADMIN.name();
        }else if(members.getRole().name().equals("USER")){
            role= Role.USER.name();
        }

        return User.builder()
                .username(members.getEmail())
                .password(members.getPassword())
                .roles(role)
                .build();
    }
}
