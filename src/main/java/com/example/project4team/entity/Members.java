package com.example.project4team.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String email;
    private String password;
}