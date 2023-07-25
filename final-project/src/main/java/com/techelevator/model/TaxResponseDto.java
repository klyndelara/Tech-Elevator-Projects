package com.techelevator.model;

import java.math.BigDecimal;

public class TaxResponseDto {
    private BigDecimal salesTex;
    private String lastUpdate;


    public TaxResponseDto(BigDecimal salesTex, String lastUpdate) {
        this.salesTex = salesTex;
        this.lastUpdate = lastUpdate;
    }

    public BigDecimal getSalesTex() {
        return salesTex;
    }

    public void setSalesTex(BigDecimal salesTex) {
        this.salesTex = salesTex;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


}
