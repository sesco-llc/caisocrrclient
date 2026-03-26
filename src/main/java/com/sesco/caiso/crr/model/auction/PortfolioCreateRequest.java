
package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "portfolioName",
    "marketName",
    "newBids",
    "newOffers",
    "participantName"
})

public class PortfolioCreateRequest {

    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonProperty("marketName")
    private String marketName;
    @JsonProperty("newBids")
    private List<NewBid> newBids;
    @JsonProperty("newOffers")
    private List<NewOffer> newOffers;
    @JsonProperty("participantName")
    private String participantName;

    @JsonProperty("portfolioName")
    public String getPortfolioName() {
        return portfolioName;
    }

    @JsonProperty("portfolioName")
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    @JsonProperty("marketName")
    public String getMarketName() {
        return marketName;
    }

    @JsonProperty("marketName")
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @JsonProperty("newBids")
    public List<NewBid> getNewBids() {
        return newBids;
    }

    @JsonProperty("newBids")
    public void setNewBids(List<NewBid> newBids) {
        this.newBids = newBids;
    }

    @JsonProperty("newOffers")
    public List<NewOffer> getNewOffers() {
        return newOffers;
    }

    @JsonProperty("newOffers")
    public void setNewOffers(List<NewOffer> newOffers) {
        this.newOffers = newOffers;
    }

    @JsonProperty("participantName")
    public String getParticipantName() {
        return participantName;
    }

    @JsonProperty("participantName")
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }



}
