package com.example.service.specification;

import com.example.model.LegalCaseSearchCriteria;
import com.example.model.LegalCase;
import com.example.model.Status;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LegalCaseSpecification implements Specification<LegalCase> {

    private LegalCaseSearchCriteria searchCriteria;

    public LegalCaseSpecification(LegalCaseSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }


        @Override
        public Predicate toPredicate(Root<LegalCase> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if (searchCriteria == null) {
                return cb.conjunction();
            }

            final List<Predicate> predicates = new ArrayList<>();


            String title = searchCriteria.getTitle();
            if (title != null && !title.trim().isEmpty()) {
                predicates.add(cb.like(root.get("title"), "%" + title + "%"));
            }


            String description = searchCriteria.getDescription();
            if (description != null && !description.trim().isEmpty()) {
                predicates.add(cb.like(root.get("description"), "%" + description + "%"));
            }


            if (searchCriteria.getAmount() != null) {
                predicates.add(cb.equal(root.get("amount"), searchCriteria.getAmount()));
            }


            if (searchCriteria.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), searchCriteria.getStatus()));
            }


            return cb.and(predicates.toArray(new Predicate[0]));
        }
    }
