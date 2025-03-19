package com.example.project4team.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membersNum;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}