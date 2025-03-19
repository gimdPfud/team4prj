package com.example.project4team.dto;

import com.example.project4team.entity.Members;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

    private Long boardNum;

    private String title;

    private String content;

    private String writer;

    private Long membersNum;

    private LocalDateTime regTime;

}
