package com.example.project4team.dto;


import com.example.project4team.entity.Members;
import com.example.project4team.entity.baseentity.Base;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FreeDTO {


    private Long freenum;

    private String title;

    private String content;

    private String writer;

    //등록날짜
    private LocalDateTime regTime;

    private Members members;

}
