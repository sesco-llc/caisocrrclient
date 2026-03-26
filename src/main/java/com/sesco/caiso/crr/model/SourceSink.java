package com.sesco.caiso.crr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class SourceSink {
    /*
    "SourceSink": "SLAP_PGST-APND",
    "Type": "LAP",
    "BiddableFlag": "Yes",
    "Zone": "PGAE-30",
    "AugmentedBusName": "BusbarSection_3408_N1",
    "EMSBusName": "1-BUS-60",
    "ParticipationFactor": 0.003,
    "TOU":
     */

    @JsonProperty("SourceSink")
    private String sourceSink;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("BiddableFlag")
    private String biddableFlag;
    @JsonProperty("Zone")
    private String zone;
    @JsonProperty("AugmentedBusName")
    private String augmentedBusName;
    @JsonProperty("EMSBusName")
    private String eMSBusName;
    @JsonProperty("ParticipationFactor")
    private Double participationFactor;
    @JsonProperty("TOU")
    private String tou;

    public String getSourceSink() {
        return sourceSink;
    }

    public void setSourceSink(String sourceSink) {
        this.sourceSink = sourceSink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBiddableFlag() {
        return biddableFlag;
    }

    public void setBiddableFlag(String biddableFlag) {
        this.biddableFlag = biddableFlag;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAugmentedBusName() {
        return augmentedBusName;
    }

    public void setAugmentedBusName(String augmentedBusName) {
        this.augmentedBusName = augmentedBusName;
    }

    public String geteMSBusName() {
        return eMSBusName;
    }

    public void seteMSBusName(String eMSBusName) {
        this.eMSBusName = eMSBusName;
    }

    public Double getParticipationFactor() {
        return participationFactor;
    }

    public void setParticipationFactor(Double participationFactor) {
        this.participationFactor = participationFactor;
    }

    public String getTou() {
        return tou;
    }

    public void setTou(String tou) {
        this.tou = tou;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<SourceSink> f = mapper.readValue("[\n" +
                                              "  {\n" +
                                              "    \"SourceSink\": \"DLAP_PGAE-APND\",\n" +
                                              "    \"Type\": \"LAP\",\n" +
                                              "    \"BiddableFlag\": \"Yes\",\n" +
                                              "    \"Zone\": \"PGAE-30\",\n" +
                                              "    \"AugmentedBusName\": \"BusbarSection_3408_N1\",\n" +
                                              "    \"EMSBusName\": \"1-BUS-60\",\n" +
                                              "    \"ParticipationFactor\": 0.000,\n" +
                                              "    \"TOU\": \"OFF\"\n" +
                                              "  },\n" +
                                              "  {\n" +
                                              "    \"SourceSink\": \"SLAP_PGST-APND\",\n" +
                                              "    \"Type\": \"LAP\",\n" +
                                              "    \"BiddableFlag\": \"Yes\",\n" +
                                              "    \"Zone\": \"PGAE-30\",\n" +
                                              "    \"AugmentedBusName\": \"BusbarSection_3408_N1\",\n" +
                                              "    \"EMSBusName\": \"1-BUS-60\",\n" +
                                              "    \"ParticipationFactor\": 0.003,\n" +
                                              "    \"TOU\": \"OFF\"\n" +
                                              "  }\n" +
                                              "]", new TypeReference<List<SourceSink>>() {
        });
        System.out.println(f);
    }
}
