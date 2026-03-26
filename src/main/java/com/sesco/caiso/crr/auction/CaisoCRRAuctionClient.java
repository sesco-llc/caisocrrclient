package com.sesco.caiso.crr.auction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sesco.bo.bop.bopftr.bopftr_Market;
import com.sesco.bo.bot.bot_FTRSubmission;
import com.sesco.caiso.crr.model.Errors;
import com.sesco.caiso.crr.model.auction.*;
import com.sesco.caiso.crr.util.ClientUtil;
import com.sesco.dom.dome.dome_HourType;
import com.sesco.maps.maps_FTRMarkets;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class CaisoCRRAuctionClient {
    String d_certName;
    String d_certPassword;
    String d_baseURL = "https://api.caiso.com/crr";
    String d_schedulingCoordinator;

    static Map<String, String> monthMap = new HashMap<>();
    static Map<String, String> quarterMap = new HashMap<>();
    static {
        monthMap.put("Jan", "01");
        monthMap.put("Feb", "02");
        monthMap.put("Mar", "03");
        monthMap.put("Apr", "04");
        monthMap.put("May", "05");
        monthMap.put("Jun", "06");
        monthMap.put("Jul", "07");
        monthMap.put("Aug", "08");
        monthMap.put("Sep", "09");
        monthMap.put("Oct", "10");
        monthMap.put("Nov", "11");
        monthMap.put("Dec", "12");
        quarterMap.put("Q1", "S01");
        quarterMap.put("Q2", "S02");
        quarterMap.put("Q3", "S03");
        quarterMap.put("Q4", "S04");
    }


    public CaisoCRRAuctionClient(String certName, String certPassword, String baseURL, String schedulingCoordinator ) {
        d_certName = certName;
        d_certPassword = certPassword;
        d_baseURL = baseURL;
        d_schedulingCoordinator = schedulingCoordinator;
    }

    public List<Portfolio> getPortfolioList(String marketName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolioList")
                .addParameter("marketName", marketName)
                .addParameter("participantName", d_schedulingCoordinator)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/json");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), new TypeReference<List<Portfolio>>() {
                });
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public Portfolio getPortfolio(String marketName, String portfolioName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolio")
                .addParameter("marketName", marketName)
                .addParameter("participantName", d_schedulingCoordinator)
                .addParameter("portfolioName", portfolioName)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/json");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), Portfolio.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }


    public PortfolioStatus getPortfolioStatus(String marketName, String portfolioName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolioStatus")
                .addParameter("marketName", marketName)
                .addParameter("participantName", d_schedulingCoordinator)
                .addParameter("portfolioName", portfolioName)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/json");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), PortfolioStatus.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public PortfolioStatus createPortfolio(String marketName, String portfolioName, Collection<bot_FTRSubmission> submissions,
                                           maps_FTRMarkets marketsMap) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolioCreation")
                .build();

        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpPost, "application/json", "application/json");

            PortfolioCreateRequest createRequest = createRequest(marketName, portfolioName, submissions, marketsMap);
            httpPost.setEntity(new StringEntity(ClientUtil.mapper.writeValueAsString(createRequest)));
            CloseableHttpResponse resp = httpclient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), PortfolioStatus.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    private PortfolioCreateRequest createRequest(String marketName, String portfolioName, Collection<bot_FTRSubmission> submissions,
                                                 maps_FTRMarkets marketsMap) {
        PortfolioCreateRequest request = new PortfolioCreateRequest();
        request.setMarketName(marketName);
        request.setPortfolioName(portfolioName);
        request.setParticipantName(d_schedulingCoordinator);
        Map<Boolean, List<bot_FTRSubmission>> customMap = submissions.stream()
                .collect(Collectors.groupingBy(
                        bot_FTRSubmission::isBidCurve)
                );
        List<bot_FTRSubmission> bidList  = customMap.get(true);
        List<bot_FTRSubmission> offerList  = customMap.get(false);
        List<NewBid> newBids = new ArrayList<>();
        for(bot_FTRSubmission bidSubmission : bidList) {
            NewBid newBid = new NewBid();

            newBid.setAssetOwner(d_schedulingCoordinator);

            newBid.setSource(bidSubmission.getSourceNode().getShortName());
            newBid.setSink(bidSubmission.getSinkNode().getShortName());
            newBid.setHedgeType("OBL");
            newBid.setTou(bidSubmission.getTradeHourType().equals(dome_HourType.Peak) ? "ON" : "OFF");
            bopftr_Market market = marketsMap.get( bidSubmission.getMarketID());
            String periodStr = convertMonthYearToFormattedPeriodString(market.getMarketName());
            newBid.setPeriod(periodStr);
            newBid.setCrrType("AUC");
            List<BidCurve> bcl = new ArrayList<>();
            BidCurve bc = new BidCurve();
            bc.setMw(0.0);
            bc.setPrice(bidSubmission.getPricePerMW());
            bcl.add(bc);
            BidCurve bc2 = new BidCurve();
            bc2.setMw(bidSubmission.getMw());
            bc2.setPrice(bidSubmission.getPricePerMW());
            bcl.add(bc2);
            newBid.setBidCurve(bcl);

            newBid.setDescription(String.valueOf(bidSubmission.getSubmissionID()));
            newBids.add(newBid);
        }
        request.setNewBids(newBids);

        List<NewOffer> newOffers = new ArrayList<>();
        for(bot_FTRSubmission offerSubmission : offerList) {
            NewOffer newOffer = new NewOffer();
            newOffer.setAssetOwner(d_schedulingCoordinator);
            newOffer.setCrrId(offerSubmission.getIsoSourceTradeID());
            newOffer.setSource(offerSubmission.getSourceNode().getShortName());
            newOffer.setSink(offerSubmission.getSinkNode().getShortName());
            newOffer.setHedgeType("OBL");
            newOffer.setTou(offerSubmission.getTradeHourType().equals(dome_HourType.Peak) ? "ON" : "OFF");
            bopftr_Market market = marketsMap.get( offerSubmission.getMarketID());
            String periodStr = convertMonthYearToFormattedPeriodString(market.getMarketName());
            newOffer.setPeriod(periodStr);
            newOffer.setCrrType("AUC");
            List<BidCurve> bcl = new ArrayList<>();
            BidCurve bc = new BidCurve();
            bc.setMw(0.0);
            bc.setPrice(offerSubmission.getPricePerMW());
            bcl.add(bc);
            BidCurve bc2 = new BidCurve();
            bc2.setMw(Math.abs(offerSubmission.getMw()));
            bc2.setPrice(offerSubmission.getPricePerMW());
            bcl.add(bc2);
            newOffer.setBidCurve(bcl);

            newOffer.setDescription(String.valueOf(offerSubmission.getSubmissionID()));
            newOffers.add(newOffer);
        }
        request.setNewOffers(newOffers);
        return request;
    }

    private static String convertMonthYearToFormattedPeriodString(String monthYear) {

        String[] parts = monthYear.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid month-year format: " + monthYear);
        }

        String period = parts[0];
        String year = parts[1];

        if (monthMap.containsKey(period)) {
            String monthNumber = monthMap.get(period);
            return year + "_M" + monthNumber;
        } else if (quarterMap.containsKey(period)) {
            String seasonNumber = quarterMap.get(period);
            return year + "_" + seasonNumber;
        } else {
            throw new IllegalArgumentException("Invalid period: " + period);
        }
    }

    public PortfolioStatus submitPortfolio(String marketName, String portfolioName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolioSubmission")
                .build();

        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpPost, "application/json", "application/json");
            PortfolioSubmissionRetractionRequest submissionRetractionRequest = new PortfolioSubmissionRetractionRequest();
            submissionRetractionRequest.setMarketName(marketName);
            submissionRetractionRequest.setParticipantName(portfolioName);
            submissionRetractionRequest.setPortfolioName(portfolioName);
            httpPost.setEntity(new StringEntity(ClientUtil.mapper.writeValueAsString(submissionRetractionRequest)));
            CloseableHttpResponse resp = httpclient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), PortfolioStatus.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public PortfolioStatus retractPortfolio(String marketName, String portfolioName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolioRetraction")
                .build();

        HttpPost httpPost = new HttpPost(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpPost, "application/json", "application/json");
            PortfolioSubmissionRetractionRequest submissionRetractionRequest = new PortfolioSubmissionRetractionRequest();
            submissionRetractionRequest.setMarketName(marketName);
            submissionRetractionRequest.setParticipantName(portfolioName);
            submissionRetractionRequest.setPortfolioName(portfolioName);
            httpPost.setEntity(new StringEntity(ClientUtil.mapper.writeValueAsString(submissionRetractionRequest)));
            CloseableHttpResponse resp = httpclient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), PortfolioStatus.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public PortfolioStatus deletePortfolio(String marketName, String portfolioName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolio")
                .addParameter("marketName", marketName)
                .addParameter("participantName", d_schedulingCoordinator)
                .addParameter("portfolioName", portfolioName)
                .build();

        HttpDelete httpDelete = new HttpDelete(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpDelete, "application/json", "application/json");

            CloseableHttpResponse resp = httpclient.execute(httpDelete);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), PortfolioStatus.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public PortfolioRequiredCredit getPortfolioRequiredCredit(String marketName, String portfolioName) throws Exception {
        String path = "/auction/v1.0/portfolioRequiredCredit";
        URI uri = new URIBuilder(d_baseURL + path)
                .addParameter("marketName", marketName)
                .addParameter("participantName", d_schedulingCoordinator)
                .addParameter("portfolioName", portfolioName)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/json");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), PortfolioRequiredCredit.class);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //CaisoECALapi_11_26_26.pfx
        //CaisoNCALapi_11_25_26.pfx
        //CaisoSCALapi_11_25_26.pfx
        //CaisoWCALapi_11_26_26.pfx
        //CaisoYCALapi_11_26_26.pfx
        CaisoCRRAuctionClient client = new CaisoCRRAuctionClient(
                "/sesco/certs/CaisoWCALapi_11_26_26.pfx",
                "Solomon01",
                "https://api.caiso.com/crr",
                "WCAL");

//        List<Portfolio> p = client.getPortfolioList("AUC_MN_2026_M03");
//        Portfolio p = client.getPortfolio("AUC_MN_2026_M03", "March MNTHLY 26");
//        PortfolioStatus p = client.getPortfolioStatus("AUC_MN_2026_M03", "March MNTHLY 26");
        PortfolioRequiredCredit p = client.getPortfolioRequiredCredit("AUC_MN_2026_M03", "March MNTHLY 26");
        System.out.println("Active Pot:" + p);
        //March MNTHLY 26

    }
}
