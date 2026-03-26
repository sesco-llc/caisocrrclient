package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bidId",
        "assetOwner",
        "source",
        "sink",
        "hedgeType",
        "tou",
        "period",
        "crrType",
        "bidCurve",
        "description"
})
public class Bid {

    @JsonProperty("bidId")
    private Integer bidId;
    @JsonProperty("assetOwner")
    private String assetOwner;
    @JsonProperty("source")
    private String source;
    @JsonProperty("sink")
    private String sink;
    @JsonProperty("hedgeType")
    private String hedgeType;
    @JsonProperty("tou")
    private String tou;
    @JsonProperty("period")
    private String period;
    @JsonProperty("crrType")
    private String crrType;
    @JsonProperty("bidCurve")
    private List<BidCurve> bidCurve;
    @JsonProperty("description")
    private String description;


    @JsonProperty("bidId")
    public Integer getBidId() {
        return bidId;
    }

    @JsonProperty("bidId")
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    @JsonProperty("assetOwner")
    public String getAssetOwner() {
        return assetOwner;
    }

    @JsonProperty("assetOwner")
    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("sink")
    public String getSink() {
        return sink;
    }

    @JsonProperty("sink")
    public void setSink(String sink) {
        this.sink = sink;
    }

    @JsonProperty("hedgeType")
    public String getHedgeType() {
        return hedgeType;
    }

    @JsonProperty("hedgeType")
    public void setHedgeType(String hedgeType) {
        this.hedgeType = hedgeType;
    }

    @JsonProperty("tou")
    public String getTou() {
        return tou;
    }

    @JsonProperty("tou")
    public void setTou(String tou) {
        this.tou = tou;
    }

    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    @JsonProperty("crrType")
    public String getCrrType() {
        return crrType;
    }

    @JsonProperty("crrType")
    public void setCrrType(String crrType) {
        this.crrType = crrType;
    }

    @JsonProperty("bidCurve")
    public List<BidCurve> getBidCurve() {
        return bidCurve;
    }

    @JsonProperty("bidCurve")
    public void setBidCurve(List<BidCurve> bidCurve) {
        this.bidCurve = bidCurve;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }


}
