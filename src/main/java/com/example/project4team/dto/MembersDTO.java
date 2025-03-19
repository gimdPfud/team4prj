package com.example.project4team.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersDTO {
    private Long membersNum;
    private String name;
    private String email;
    private String password;
}
