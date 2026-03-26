package com.sesco.caiso.crr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    @JsonProperty("errorDescription")
    private String errorDescription;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("severity")
    private String severity;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
