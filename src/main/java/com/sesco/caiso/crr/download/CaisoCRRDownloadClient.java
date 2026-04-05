package com.sesco.caiso.crr.download;

import com.sesco.caiso.crr.model.Errors;
import com.sesco.caiso.crr.util.ClientUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URI;

public class CaisoCRRDownloadClient {
    String d_certName;
    String d_certPassword;
    String d_baseURL = "https://api.caiso.com/crr";
    String d_schedulingCoordinator;


    public CaisoCRRDownloadClient(String certName, String certPassword, String baseURL, String schedulingCoordinator) {
        d_certName = certName;
        d_certPassword = certPassword;
        d_baseURL = baseURL;
        d_schedulingCoordinator = schedulingCoordinator;
    }

    public void getPublicAuctionResult(String marketName, String directoryName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/marketdata/v1.0/publicAuctionResult")
                .addParameter("marketName", marketName)
                .addParameter("fileFormat", "CSV")
                .addParameter("publicAuctionResultDownloadType", "PUBLIC_AUCTION_RESULT")
                .addParameter("participantName", d_schedulingCoordinator)
                .build();
        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/octet-stream");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                byte[] bytes = EntityUtils.toByteArray(resp.getEntity());
                File f = new File(directoryName);
                if (!f.exists()) {
                    f.mkdirs();
                }
                FileUtils.writeByteArrayToFile(new File(f, "publicResults_" + marketName + ".zip"), bytes);
//
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                System.out.println(s);
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public void getPrivateAuctionResult(String marketName, String directoryName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/marketdata/v1.0/privateAuctionResult")
                .addParameter("marketName", marketName)
                .addParameter("fileFormat", "CSV")
                .addParameter("privateAuctionResultDownloadType", "PRIVATE_AUCTION_RESULT")
                .addParameter("participantName", d_schedulingCoordinator)
                .build();
        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/octet-stream");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                byte[] bytes = EntityUtils.toByteArray(resp.getEntity());
                File f = new File(directoryName);
                if (!f.exists()) {
                    f.mkdirs();
                }
                FileUtils.writeByteArrayToFile(new File(f, "privateResults_" + d_schedulingCoordinator + "_" + marketName + ".zip"), bytes);
            } else {
                String s = EntityUtils.toString(resp.getEntity());
                System.out.println(s);
                if (!s.isEmpty()) {
                    Errors f = ClientUtil.mapper.readValue(s, Errors.class);
                    throw new Exception(f.getErrorList().get(0).getErrorDescription());
                } else {
                    throw new Exception("No Results returned");
                }
            }
        }
    }

    public void geClearingPriceResult(String marketName, String directoryName) throws Exception {
        URI uri = new URIBuilder(d_baseURL + "/marketdata/v1.0/publicAuctionResult")
                .addParameter("marketName", marketName)
                .addParameter("fileFormat", "CSV")
                .addParameter("publicAuctionResultDownloadType", "CLEARING_PRICE")
                .addParameter("participantName", d_schedulingCoordinator)
                .build();
        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/octet-stream");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                byte[] bytes = EntityUtils.toByteArray(resp.getEntity());
                File f = new File(directoryName);
                if (!f.exists()) {
                    f.mkdirs();
                }
                FileUtils.writeByteArrayToFile(new File(f, "clearingprices_" + marketName + ".zip"), bytes);
//
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

    public String getSourceSink(String marketName, String directoryName) throws Exception {

        URI uri = new URIBuilder(d_baseURL + "/marketdata/v1.0/publicMarketData")
                .addParameter("marketName", marketName)
                .addParameter("fileFormat", "JSON")
                .addParameter("publicMarketDownloadType", "SOURCE_SINK")
                .addParameter("participantName", d_schedulingCoordinator)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/octet-stream");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                if (resp.getFirstHeader("Content-Type").getValue().startsWith("text/html")) {
                    throw new Exception(EntityUtils.toString(resp.getEntity()));
                }
                byte[] bytes = EntityUtils.toByteArray(resp.getEntity());
                File f = new File(directoryName);
                if (!f.exists()) {
                    f.mkdirs();
                }
                FileUtils.writeByteArrayToFile(new File(f, marketName + "_SourceSink.zip"), bytes);
               return marketName + "_SourceSink.zip";
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
        CaisoCRRDownloadClient client = new CaisoCRRDownloadClient(
                "/sesco/certs/CaisoWCALapi_11_26_26.pfx",
                "Solomon01",
                "https://api.caiso.com/crr",
                "WCAL");
        String ss = client.getSourceSink("AUC_MN_2026_M04", "/sesco/temp/caiso/crr");
//        System.out.println(ss);
//        client.getPublicAuctionResult("AUC_MN_2026_M04", "/sesco/temp/caiso/crr");
//        client.geClearingPriceResult("AUC_MN_2026_M04", "/sesco/temp/caiso/crr");
//        client.geClearingPriceResult("AUC_AN_2026", "/sesco/temp/caiso/crr");
//        client.getPrivateAuctionResult("AUC_AN_2026_M04", "/sesco/temp/caiso/crr");
          System.out.println(ss);
    }
}
