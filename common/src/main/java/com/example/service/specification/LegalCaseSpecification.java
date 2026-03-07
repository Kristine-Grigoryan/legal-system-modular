package com.example.service.specification;

import com.example.dto.LegalCaseSearchCriteria;
import com.model.LegalCase;
import com.model.Status;
import jakarta.persistence.criteria.*;
import org.jspecify.annotations.Nullable;
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
        public @Nullable Predicate toPredicate(Root<LegalCase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            Path<String> title = root.get("title");
            Path<String> description = root.get("description");
            Path<BigDecimal>amountPath= root.get("amount");
            Path<Status> statusPath = root.get("status");

            final List<Predicate> predicates = new ArrayList<Predicate>();

            if(searchCriteria.getTitle() != null && !searchCriteria.getTitle().isBlank()) {
                predicates.add(criteriaBuilder.like(title, "%" + searchCriteria.getTitle() + "%"));
            }

            if(searchCriteria.getDescription() != null && !searchCriteria.getDescription().isBlank()) {
                predicates.add(criteriaBuilder.like(description, "%" + searchCriteria.getDescription() + "%"));
            }

            if(searchCriteria.getAmount() != null){
                predicates.add(criteriaBuilder.equal(root.get("amount"), searchCriteria.getAmount()));
            }
            if(searchCriteria.getStatus() != null){
                predicates.add(criteriaBuilder.equal(statusPath, searchCriteria.getStatus()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }


