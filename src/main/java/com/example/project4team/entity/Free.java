package com.example.project4team.entity;


import com.example.project4team.entity.baseentity.Base;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Free extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freenum;

    private String title;

    private String content;

    private String writer;

    @ManyToOne
    @JoinColumn(name = "membersNum")
    private Members members;

}
