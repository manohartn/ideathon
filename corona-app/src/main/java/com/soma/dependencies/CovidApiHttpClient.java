package com.soma.dependencies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CovidApiHttpClient {

    private String three_letter_country_code;

    public String execute() {
        CloseableHttpResponse response = null;
        final CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .build();

        String result = null;
        final String covidApiUri = "https://covidapi.info/api/v1/country/" + three_letter_country_code;
        System.out.println("Making request to " + covidApiUri);

        try {
            try {
                HttpUriRequest httpUriRequest = new HttpGet(covidApiUri);

                try {
                    response = httpClient.execute(httpUriRequest);
                } catch (final Exception ex) {
                    System.out.println("Error calling uri");
                }

                debugPrintResponse(response);


                try {

                    final HttpEntity entity = response != null?
                            response.getEntity() : null;
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

    private void debugPrintResponse(CloseableHttpResponse response) {

        if (response != null) {
            // Get HttpResponse Status
            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 200
            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK
        }
    }
}
