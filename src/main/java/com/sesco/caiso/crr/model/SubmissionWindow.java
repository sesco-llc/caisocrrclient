
package com.sesco.caiso.crr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "openDate",
    "closeDate"
})

public class SubmissionWindow {

    @JsonProperty("openDate")
    private String openDate;
    @JsonProperty("closeDate")
    private String closeDate;

    @JsonProperty("openDate")
    public String getOpenDate() {
        return openDate;
    }

    @JsonProperty("openDate")
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    @JsonProperty("closeDate")
    public String getCloseDate() {
        return closeDate;
    }

    @JsonProperty("closeDate")
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

}
