//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.net.ssl.SSLContext;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyStore;
//import java.util.UUID;
//
//public class HttpClientTestAPI {
//
//    private static final Logger logger = LogManager.getLogger(HttpClientTestAPI.class);
//
//    public static void main(String[] args) throws Exception {
//        // Generate a UUID for the request ID
//        String requestId = UUID.randomUUID().toString();
//
//        // Load the certificate
//        String certFilename = "/Users/skondreddy/sesco/certs/CaisoECALapi_8_31_25.pfx";
//        String certPassword = "Solomon01";
//
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        try (InputStream keyStoreStream = new FileInputStream(certFilename)) {
//            keyStore.load(keyStoreStream, certPassword.toCharArray());
//        }
//
//        SSLContext sslContext = SSLContexts.custom()
//                .loadKeyMaterial(keyStore, certPassword.toCharArray())
//                .build();
//
//        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
//                sslContext,
//                new String[]{"TLSv1.2"},
//                null,
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier()
//        );
//
//
//            // Execute the request
//            try (CloseableHttpClient httpClient = HttpClients.custom()
//                    .setSSLSocketFactory(sslSocketFactory)
//                    .build()) {
//
// //            requestId = "05935efa-d869-47a2-ab39-ae31e2cc8658";
//            // Create the HTTP GET request
////            String url = "https://mapstage-api.caiso.com/crr/status";//?caiso-request-id=" + requestId";
////            HttpGet httpGet = new HttpGet(url);
////            httpGet.setHeader("caiso-request-id", requestId);
////            logger.info("URL: " + url);
////            logger.info("caiso-request-id: " + requestId);
//
//                // Define parameters
//                String marketName = "AUC_MN_2025_M03";
//                String portfolioName = "March test";
//                String participantName = "ECAL";
//
//                // Encode parameters
//                String encodedMarketName = URLEncoder.encode(marketName, StandardCharsets.UTF_8.toString());
//                String encodedPortfolioName = URLEncoder.encode(portfolioName, StandardCharsets.UTF_8.toString());
//                String encodedParticipantName = URLEncoder.encode(participantName, StandardCharsets.UTF_8.toString());
//
//                // Create the HTTP GET request with parameters
//                String url = String.format("https://mapstage-api.caiso.com/crr/auction/v1.0/portfolio?marketName=%s&portfolioName=%s&participantName=%s",
//                        encodedMarketName, encodedPortfolioName, encodedParticipantName);
//                HttpGet httpGet = new HttpGet(url);
//                httpGet.setHeader("caiso-request-id", requestId);
//                logger.info("URL: " + url);
//                logger.info("caiso-request-id: " + requestId);
//
//                // Execute the request
//                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    logger.info("Response Code: " + statusCode);
//
//                    // Capture the response
//                    if (statusCode == 200) {
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                        StringBuilder responseContent = new StringBuilder();
//                        String line;
//                        while ((line = reader.readLine()) != null) {
//                            responseContent.append(line);
//                        }
//                        // Parse the JSON response
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        JsonNode jsonNode = objectMapper.readTree(responseContent.toString());
//                        logger.info("Parsed JSON: " + jsonNode.toPrettyString());
//                    } else {
//                        logger.error("Failed to get a valid response.");
//                    }
//                }
//            }
//        }
//    }