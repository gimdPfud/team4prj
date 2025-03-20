package com.example.project4team.entity;

import com.example.project4team.entity.baseentity.Base;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todonum;

    private String title;
    private String content;
    private String witer;
    private String yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membersNum")
    private Members members;



}
