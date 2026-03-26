package com.sesco.caiso.crr.model.download;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "period",
        "tou"
})

public class Product {

    @JsonProperty("period")
    private Period period;
    @JsonProperty("tou")
    private String tou;

    @JsonProperty("period")
    public Period getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(Period period) {
        this.period = period;
    }

    @JsonProperty("tou")
    public String getTou() {
        return tou;
    }

    @JsonProperty("tou")
    public void setTou(String tou) {
        this.tou = tou;
    }

}
