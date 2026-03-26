package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortfolioSubmissionRetractionRequest {

    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonProperty("marketName")
    private String marketName;
    @JsonProperty("participantName")
    private String participantName;

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

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }
}
