package server.home.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;


public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    public InputStreamReader getResponceFromGet(String url) {
        HttpClient clientHttp = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = clientHttp.execute(request);
            LOGGER.debug("Status Code : " + response.getStatusLine().getStatusCode() + "URL: " + url);
            if (response.getStatusLine().getStatusCode() != 400) {
                return new InputStreamReader(response.getEntity().getContent());
            } else {
                throw new RuntimeException("El recurso no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}