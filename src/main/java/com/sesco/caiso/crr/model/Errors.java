package com.sesco.caiso.crr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Errors {

    @JsonProperty("errorList")
    private List<Error> errorList;
    @JsonProperty("creationTime")
    private String creationTime;
    @JsonProperty("serviceVersion")
    private String serviceVersion;

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Errors f = mapper.readValue("{\"errorList\":[{\"errorDescription\":\"Access Denied. Please contact your Local Security Administrator.\",\"errorCode\":\"SEC0002\",\"severity\":\"ERROR\"}],\"creationTime\":\"2026-03-26T12:20:22Z\",\"serviceVersion\":\"V1.0\"}",
                Errors.class);
        System.out.println(f);
    }
}
