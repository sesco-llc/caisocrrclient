package model;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Bid {
//  @SerializedName("bidId")
  private Long bidId = null;

//  @SerializedName("assetOwner")
  private String assetOwner = null;

//  @SerializedName("source")
  private String source = null;

//  @SerializedName("sink")
  private String sink = null;

//  @SerializedName("hedgeType")
  private HedgeType hedgeType = null;

//  @SerializedName("tou")
  private TimeOfUse tou = null;

//  @SerializedName("period")
  private String period = null;

//  @SerializedName("crrType")
  private CrrType crrType = null;

//  @SerializedName("bidCurve")
  private List<BidPoint> bidCurve = new ArrayList<>();

//  @SerializedName("description")
  private String description = null;

  public Bid bidId(Long bidId) {
    this.bidId = bidId;
    return this;
  }

    public Long getBidId() {
    return bidId;
  }

  public void setBidId(Long bidId) {
    this.bidId = bidId;
  }

  public Bid assetOwner(String assetOwner) {
    this.assetOwner = assetOwner;
    return this;
  }

  public String getAssetOwner() {
    return assetOwner;
  }

  public void setAssetOwner(String assetOwner) {
    this.assetOwner = assetOwner;
  }

  public Bid source(String source) {
    this.source = source;
    return this;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Bid sink(String sink) {
    this.sink = sink;
    return this;
  }

  public String getSink() {
    return sink;
  }

  public void setSink(String sink) {
    this.sink = sink;
  }

  public Bid hedgeType(HedgeType hedgeType) {
    this.hedgeType = hedgeType;
    return this;
  }

  public HedgeType getHedgeType() {
    return hedgeType;
  }

  public void setHedgeType(HedgeType hedgeType) {
    this.hedgeType = hedgeType;
  }

  public Bid tou(TimeOfUse tou) {
    this.tou = tou;
    return this;
  }

  public TimeOfUse getTou() {
    return tou;
  }

  public void setTou(TimeOfUse tou) {
    this.tou = tou;
  }

  public Bid period(String period) {
    this.period = period;
    return this;
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public Bid crrType(CrrType crrType) {
    this.crrType = crrType;
    return this;
  }

  public CrrType getCrrType() {
    return crrType;
  }

  public void setCrrType(CrrType crrType) {
    this.crrType = crrType;
  }

  public Bid bidCurve(List<BidPoint> bidCurve) {
    this.bidCurve = bidCurve;
    return this;
  }

  public Bid addBidCurveItem(BidPoint bidCurveItem) {
    this.bidCurve.add(bidCurveItem);
    return this;
  }

  public List<BidPoint> getBidCurve() {
    return bidCurve;
  }

  public void setBidCurve(List<BidPoint> bidCurve) {
    this.bidCurve = bidCurve;
  }

  public Bid description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bid bid = (Bid) o;
    return Objects.equals(bidId, bid.bidId) &&
            Objects.equals(assetOwner, bid.assetOwner) &&
            Objects.equals(source, bid.source) &&
            Objects.equals(sink, bid.sink) &&
            Objects.equals(hedgeType, bid.hedgeType) &&
            Objects.equals(tou, bid.tou) &&
            Objects.equals(period, bid.period) &&
            Objects.equals(crrType, bid.crrType) &&
            Objects.equals(bidCurve, bid.bidCurve) &&
            Objects.equals(description, bid.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bidId, assetOwner, source, sink, hedgeType, tou, period, crrType, bidCurve, description);
  }

  @Override
  public String toString() {
    return "class Bid {\n" +
            "    bidId: " + toIndentedString(bidId) + "\n" +
            "    assetOwner: " + toIndentedString(assetOwner) + "\n" +
            "    source: " + toIndentedString(source) + "\n" +
            "    sink: " + toIndentedString(sink) + "\n" +
            "    hedgeType: " + toIndentedString(hedgeType) + "\n" +
            "    tou: " + toIndentedString(tou) + "\n" +
            "    period: " + toIndentedString(period) + "\n" +
            "    crrType: " + toIndentedString(crrType) + "\n" +
            "    bidCurve: " + toIndentedString(bidCurve) + "\n" +
            "    description: " + toIndentedString(description) + "\n" +
            "}";
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}