package com.sesco.caiso.crr.model.auction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "mw",
        "price"
})
public class BidCurve {

    @JsonProperty("mw")
    private Double mw;
    @JsonProperty("price")
    private Double price;

    @JsonProperty("mw")
    public Double getMw() {
        return mw;
    }

    @JsonProperty("mw")
    public void setMw(Double mw) {
        this.mw = mw;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }


}
