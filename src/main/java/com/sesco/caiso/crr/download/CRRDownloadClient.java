package com.sesco.caiso.crr.download;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sesco.caiso.crr.model.Errors;
import com.sesco.caiso.crr.model.SourceSink;
import com.sesco.caiso.crr.util.ClientUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CRRDownloadClient {
    String d_certName;
    String d_certPassword;
    String d_baseURL = "https://api.caiso.com/crr";
    String d_schedulingCoordinator;



    public CRRDownloadClient(String certName, String certPassword, String baseURL, String schedulingCoordinator) {
        d_certName = certName;
        d_certPassword = certPassword;
        d_baseURL = baseURL;
        d_schedulingCoordinator = schedulingCoordinator;
    }

    public List<SourceSink> getSourceSink(String marketName, String directoryName) throws Exception {

        URI uri = new URIBuilder(d_baseURL + "/marketdata/v1.0/publicMarketData")
                .addParameter("marketName", marketName)
                .addParameter("fileFormat", "JSON")
                .addParameter("publicMarketDownloadType", "SOURCE_SINK")
                .addParameter("participantName", d_schedulingCoordinator)
                .build();

        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpClient httpclient = ClientUtil.getClientBuilder(d_certName, d_certPassword).build()) {
            ClientUtil.addHeaders(httpget, "application/json", "application/json");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            if (resp.getStatusLine().getStatusCode() == 200) {
                byte[] bytes = EntityUtils.toByteArray(resp.getEntity());
                File f = new File(directoryName);
                if (!f.exists()) {
                    f.mkdirs();
                }
                FileUtils.writeByteArrayToFile(new File(f, "ss.zip"), bytes);
                StringBuilder stb = new StringBuilder(1024);
                try (ZipFile zipFile = new ZipFile(new File(f, "ss.zip"))) {
                    Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
                    ZipEntry entry = zipFile.getEntry(zipEntries.nextElement().getName());

                    if (entry != null) {
                        try (InputStream stream = zipFile.getInputStream(entry)) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                stb.append(line);
                            }
                        }
                    } else {
                        System.out.println("File not found in the archive.");
                    }
                }
                return ClientUtil.mapper.readValue(stb.toString(), new TypeReference<List<SourceSink>>() {});
            } else {
                Errors f = ClientUtil.mapper.readValue(EntityUtils.toString(resp.getEntity()), Errors.class);
                throw new Exception(f.getErrorList().get(0).getErrorDescription());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CRRDownloadClient client = new CRRDownloadClient("/sesco/certs/CaisoWCALapi_11_26_26.pfx",
                "Solomon01",
                "https://api.caiso.com/crr",
                "WCAL");
        List<SourceSink> ss = client.getSourceSink("AUC_MN_2026_M03", "/sesco/temp/caiso/crr");
        System.out.println(ss);
    }
}
