package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortfolioRequiredCredit {

    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonProperty("marketName")
    private String marketName;
    @JsonProperty("requiredCredit")
    private Double requiredCredit;

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Double getRequiredCredit() {
        return requiredCredit;
    }

    public void setRequiredCredit(Double requiredCredit) {
        this.requiredCredit = requiredCredit;
    }
}
