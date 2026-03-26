package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortfolioStatus {
    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonProperty("marketName")
    private String marketName;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("errorMessage")
    private String errorMessage;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
