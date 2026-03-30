
package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
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
public class NewBid {

    @JsonProperty("assetOwner")
    private String assetOwner;
    @JsonProperty("source")
    private String source;
    @JsonProperty("sink")
    private String sink;
    @JsonProperty("hedgeType")
    private HedgeType hedgeType;
    @JsonProperty("tou")
    private TOU tou;

    public void setCrrType(CRRType crrType) {
        this.crrType = crrType;
    }

    @JsonProperty("period")
    private String period;
    @JsonProperty("crrType")
    private CRRType crrType;
    @JsonProperty("bidCurve")
    private List<BidCurve> bidCurve;
    @JsonProperty("description")
    private String description;

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
    public HedgeType getHedgeType() {
        return hedgeType;
    }

    @JsonProperty("hedgeType")
    public void setHedgeType(HedgeType hedgeType) {
        this.hedgeType = hedgeType;
    }

    @JsonProperty("tou")
    public TOU getTou() {
        return tou;
    }

    @JsonProperty("tou")
    public void setTou(TOU tou) {
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

    public CRRType getCrrType() {
        return crrType;
    }
}
