package com.example.project4team.repository;

import com.example.project4team.entity.Free;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FreeRepository extends JpaRepository<Free,Long> {

    //레포지토리 페이지 처리 하기

    @Query("select f from Free f ")
    public Page<Free> pageFree(Pageable pageable);


}
