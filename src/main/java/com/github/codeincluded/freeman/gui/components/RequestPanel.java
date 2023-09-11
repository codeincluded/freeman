package com.github.codeincluded.freeman.gui.components;

import com.github.codeincluded.freeman.data.Request;
import com.github.codeincluded.freeman.data.Response;
import com.github.codeincluded.freeman.net.SimpleHttpClient;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.net.http.HttpResponse;

public class RequestPanel extends JPanel {

    private final JTextPane textPane = new JTextPane();
    private final SimpleHttpClient httpClient;

    public RequestPanel(SimpleHttpClient simpleHttpClient) {
        this.httpClient = simpleHttpClient;
        setLayout(new BorderLayout());

        var requestConfig = new JPanel();
        requestConfig.setLayout(new BoxLayout(requestConfig, BoxLayout.Y_AXIS));
        requestConfig.add(new UrlPanel(this::sendRequest, this::setResponse, this::setError));
        requestConfig.add(new RequestDetailsPanel());

        var splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(requestConfig);
        splitPane.add(new JScrollPane(textPane));

        add(splitPane);

        splitPane.setDividerLocation(160);
    }

    @SneakyThrows
    private Response sendRequest(Request request) {
        HttpResponse<String> httpResponse = httpClient.doGet(request.getUrl());
        return Response.builder()
                .body(httpResponse.body())
                .build();
    }

    private void setResponse(Response response) {
        textPane.setText(response.getBody());
    }

    private void setError(Throwable throwable) {
        // nothing here yet
    }
}
