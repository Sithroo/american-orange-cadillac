package io.sithroo.aoc.commons.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {
    private final String baseUrl;

    public HttpClient(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected String post(final String resource, final String body) {
        try {
            HttpResponse response = Request.Post(baseUrl + resource)
                    .bodyString(body, ContentType.APPLICATION_JSON)
                    .execute().returnResponse();

            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String get(final String resource) {
        try {
            HttpResponse response = Request.Get(baseUrl + resource)
                    .execute().returnResponse();
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
