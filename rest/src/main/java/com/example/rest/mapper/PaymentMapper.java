package com.example.rest.mapper;


import com.example.model.LegalCase;
import com.example.model.Payment;
import com.example.rest.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.math.BigDecimal;
import java.math.RoundingMode;


@Mapper(componentModel = "spring", imports = {BigDecimal.class, RoundingMode.class})
public interface  PaymentMapper {


        @Mapping(source = "caseId", target = "legalCase")
        Payment toEntity(PaymentDto dto);

        @Mapping(source = "legalCase.id", target = "caseId")
        PaymentDto toDTO(Payment entity);

        default LegalCase map(Long caseId) {
            if (caseId == null) return null;

            LegalCase lc = new LegalCase();
            lc.setId(caseId);
            return lc;
        }

        default Long map(LegalCase legalCase) {
            return legalCase != null ? legalCase.getId() : null;
        }
    }