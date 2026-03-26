package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "portfolioName",
        "marketName",
        "bids",
        "offers"
})
public class Portfolio {

    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonProperty("marketName")
    private String marketName;
    @JsonProperty("bids")
    private List<Bid> bids;
    @JsonProperty("offers")
    private List<Offer> offers;


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

    @JsonProperty("bids")
    public List<Bid> getBids() {
        return bids;
    }

    @JsonProperty("bids")
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @JsonProperty("offers")
    public List<Offer> getOffers() {
        return offers;
    }

    @JsonProperty("offers")
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}
