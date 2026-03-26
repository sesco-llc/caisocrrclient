package com.sesco.caiso.crr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "marketType",
        "marketTerm",
        "dataPublishDate",
        "submissionWindow",
        "products"
})
public class Auction {

    @JsonProperty("name")
    private String name;
    @JsonProperty("marketType")
    private String marketType;
    @JsonProperty("marketTerm")
    private String marketTerm;
    @JsonProperty("dataPublishDate")
    private String dataPublishDate;
    @JsonProperty("submissionWindow")
    private SubmissionWindow submissionWindow;
    @JsonProperty("products")
    private List<Product> products;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("marketType")
    public String getMarketType() {
        return marketType;
    }

    @JsonProperty("marketType")
    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    @JsonProperty("marketTerm")
    public String getMarketTerm() {
        return marketTerm;
    }

    @JsonProperty("marketTerm")
    public void setMarketTerm(String marketTerm) {
        this.marketTerm = marketTerm;
    }

    @JsonProperty("dataPublishDate")
    public String getDataPublishDate() {
        return dataPublishDate;
    }

    @JsonProperty("dataPublishDate")
    public void setDataPublishDate(String dataPublishDate) {
        this.dataPublishDate = dataPublishDate;
    }

    @JsonProperty("submissionWindow")
    public SubmissionWindow getSubmissionWindow() {
        return submissionWindow;
    }

    @JsonProperty("submissionWindow")
    public void setSubmissionWindow(SubmissionWindow submissionWindow) {
        this.submissionWindow = submissionWindow;
    }

    @JsonProperty("products")
    public List<Product> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
