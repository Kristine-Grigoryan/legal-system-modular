package com.example.rest.mapper;

import com.example.model.Payment;
import com.example.rest.dto.PaymentDto;
import com.example.rest.service.currency.CurrencyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, RoundingMode.class})
public abstract class PaymentMapper {

    @Autowired
    protected CurrencyService currencyService;



    @Mapping(source = "caseId", target = "legalCase.id")
    public abstract Payment toEntity(PaymentDto paymentDto);

    @Mapping(source = "legalCase.id", target = "caseId")
    @Mapping(target = "method", source = "method")
    @Mapping(target = "amountUSD", expression = "java(calculateAmount(payment.getAmount(), getRate(\"USD\")))")
    @Mapping(target = "amountEUR", expression = "java(calculateAmount(payment.getAmount(), getRate(\"EUR\")))")
    @Mapping(target = "amountRUB", expression = "java(calculateAmount(payment.getAmount(), getRate(\"RUB\")))")
    public abstract PaymentDto toDto(Payment payment);



    public abstract List<PaymentDto> toDtoList(List<Payment> payments);

    public BigDecimal calculateAmount(BigDecimal amount, Double rate) {
        if (amount == null || rate == null || rate == 0) return BigDecimal.ZERO;
        return amount.multiply(BigDecimal.valueOf(rate)).setScale(2, RoundingMode.HALF_UP);
    }
    protected Double getRate(String currencyType) {
        var currency = currencyService.getCurrency();
        if (currency == null) return 1.0;


        String rateStr = switch (currencyType) {
            case "USD" -> currency.getUsd();
            case "EUR" -> currency.getEur();
            case "RUB" -> currency.getRub();
            default -> "1.0";
        };
        return (rateStr != null && !rateStr.isEmpty()) ? Double.parseDouble(rateStr) : 1.0;

    }
    }
