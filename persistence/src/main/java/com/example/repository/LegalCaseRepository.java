package com.example.repository;
import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {

    @Query("select s from LegalCase s join s.user us where us = :user")
    List<LegalCase> findByUser(User user);



    @Transactional
    @Modifying
    Page<LegalCase> findAll(Specification<LegalCase> spec, Pageable pageable);

    List<LegalCase> findAllByStatus(Status status);
}


