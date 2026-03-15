package com.example.repository;

import com. example.model.CaseDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseDocumentRepository extends JpaRepository<CaseDocument, Long> {



}
