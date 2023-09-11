package com.github.codeincluded.freeman.net;

import com.github.codeincluded.freeman.data.Request;
import com.github.codeincluded.freeman.data.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SimpleHttpClient {

    public Response send(Request request) throws IOException, InterruptedException {
        var httpRequestBuilder = HttpRequest.newBuilder()
                .method(request.getMethod(), HttpRequest.BodyPublishers.ofString(request.getBody()))
                .uri(URI.create(request.getUrl()))
                .timeout(Duration.ofSeconds(10));

        request.getHeaders().forEach(httpRequestBuilder::setHeader);

        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NEVER)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> httpResponse = client.send(
                httpRequestBuilder.build(),
                HttpResponse.BodyHandlers.ofString()
        );

        return Response.builder().body(httpResponse.body()).build();
    }
}
