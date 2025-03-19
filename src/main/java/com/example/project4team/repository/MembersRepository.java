package com.example.project4team.repository;

import com.example.project4team.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembersRepository extends JpaRepository<Members, Long> {
    public Members findByEmail(String email);
    public List<Members> findByNameContaining (String keyword);
}
