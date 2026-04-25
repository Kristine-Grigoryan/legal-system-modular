package com.example.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

        @JsonProperty("USD")
        private String usd;

        @JsonProperty("EUR")
        private String eur;

        @JsonProperty("RUB")
        private String rub;
    }



