package com.techelevator.Service;

import com.techelevator.dao.TaxResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TaxService {
    public Double getTaxRate(String stateCode) {
        RestTemplate restTemplate = new RestTemplate();
        TaxResponseDto taxResponseDto =
                restTemplate.getForObject("https://teapi.netlify.app/api/statetax?state=" + stateCode, TaxResponseDto.class);
        Double taxRate = taxResponseDto.getSalesTax() / 100;
        return taxRate;


    }

}