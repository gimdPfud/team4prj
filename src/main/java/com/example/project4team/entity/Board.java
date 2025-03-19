package com.example.project4team.entity;

import com.example.project4team.entity.baseentity.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column(length = 50)
    private String title;

    @Column(length = 2000)
    private String content;

    @Column(length = 20)
    private String writer;

    @ManyToOne
    @JoinColumn(name = "membersNum")
    private Members members;

}
