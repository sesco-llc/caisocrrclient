package com.sesco.caiso.crr.auction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sesco.caiso.crr.model.Errors;
import com.sesco.caiso.crr.model.auction.Portfolio;
import com.sesco.caiso.crr.util.ClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.List;

public class CaisoCRRAuctionClient {
    String d_certName;
    String d_certPassword;
    String d_baseURL = "https://api.caiso.com/crr";
    String d_schedulingCoordinator;

    public CaisoCRRAuctionClient(String certName, String certPassword, String baseURL, String schedulingCoordinator) {
        d_certName = certName;
        d_certPassword = certPassword;
        d_baseURL = baseURL;
        d_schedulingCoordinator = schedulingCoordinator;
    }

    public List<Portfolio>  getPortfolioList(String marketName) throws Exception{
        URI uri = new URIBuilder(d_baseURL + "/auction/v1.0/portfolioList")
                .addParameter("marketName", marketName)
                .addParameter("participantName", d_schedulingCoordinator)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/json");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if(resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                return ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), new TypeReference<List<Portfolio>>() {});
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

    public Portfolio  getPortfolio(String marketName, String portfolioName) throws Exception{
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
                if(resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
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
        Portfolio p = client.getPortfolio("AUC_MN_2026_M03", "March MNTHLY 26");
        System.out.println("Active Pot:" +p);
        //March MNTHLY 26

    }
}
