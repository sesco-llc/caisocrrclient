package com.sesco.caiso.crr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesco.co.cop.cop_SecurityUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import javax.net.ssl.SSLContext;
import java.util.UUID;

public class ClientUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    public static HttpClientBuilder getClientBuilder(String certFileName, String certPassword) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("TLSv1.2");
        cop_SecurityUtils.initializeSSLContext(sslcontext, certFileName, certPassword, true);

        SSLConnectionSocketFactory sslConnectionSocketFactory =
                new SSLConnectionSocketFactory(sslcontext,
                        new String[]{"TLSv1.2", "SSLv3"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(sslConnectionSocketFactory);
        httpClientBuilder.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:148.0) Gecko/20100101 Firefox/148.0");
        httpClientBuilder.setDefaultCookieStore(cookieStore);
        httpClientBuilder.setRedirectStrategy(new LaxRedirectStrategy());

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(15 * 1000)
                .setConnectionRequestTimeout(15 * 1000)
                .setSocketTimeout(15 * 1000).build();

        httpClientBuilder.setDefaultRequestConfig(config);

        return httpClientBuilder;
    }

    public static  void addHeaders(HttpRequestBase request, String contentType, String acceptType){
        String requestUUID = UUID.randomUUID().toString();
        request.setHeader("caiso-request-id", requestUUID);
        if(contentType != null) {
            request.setHeader("Content-Type", contentType);
        }
        if(acceptType != null) {
            request.setHeader("Accept", acceptType);
        }
    }
}
