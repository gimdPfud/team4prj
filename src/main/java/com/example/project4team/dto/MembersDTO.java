package com.example.project4team.dto;

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

    @Size(max = 20, min = 1)
    private String email;

    @Size(max = 20, min = 1)
    private String password;
}
