package com.soma.dependencies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AlexaHttpClient {

    private String apiAccessToken;
    private String deviceId;

    public String execute() {

        final CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .build();
        String result = null;
        final String alexaAddressEndpoint = "https://api.amazonalexa.com/v1/devices/";
        final String alexaUri = alexaAddressEndpoint + deviceId + "/settings/address";
        final String authHeader = "Bearer " +  apiAccessToken;

        try {

            CloseableHttpResponse response = null;

            try {

                HttpGet httpGet = new HttpGet(alexaUri);
                httpGet.setHeader("Authorization", authHeader);

                try {
                    response = httpClient.execute(httpGet);
                } catch (final Exception ex) {
                    System.out.println("Error calling uri");
                }
                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        result = EntityUtils.toString(entity);
                        System.out.println(result);
                    }

                } catch (final Exception ex) {
                    System.out.println("Error processing response");
                }
            } finally {
                assert response != null;
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
