package com.example.project4team.dto;

import com.example.project4team.constant.Role;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersDTO {
    private Long membersNum;

    @Size(max = 20, min = 1)
    private String name;

    private String email;

    @Size(max = 20, min = 8)
    private String password;

    private String newPassword1;
    private String newPassword2;

    private Role role;
}