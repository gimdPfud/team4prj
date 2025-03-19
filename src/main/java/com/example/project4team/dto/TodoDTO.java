package com.example.project4team.dto;

import com.example.project4team.entity.Members;
import com.example.project4team.entity.baseentity.Base;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {
    private Long todonum;
    private String title;
    private String content;
    private String witer;
    private String yn;

    //등록날짜
    private LocalDateTime regTime;

    private Long membersNum;


}
