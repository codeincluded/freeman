package com.github.codeincluded.freeman.gui.components;

import com.github.codeincluded.freeman.data.Request;
import com.github.codeincluded.freeman.data.Response;
import com.github.codeincluded.freeman.net.SimpleHttpClient;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class RequestPanel extends JPanel {

    private static final int DEFAULT_DIVIDER_LOCATION = 160;

    private final JTextPane responseTextPane = new JTextPane();
    private final SimpleHttpClient httpClient;
    private final UrlPanel urlPanel;
    private final RequestDetailsPanel requestDetailsPanel = new RequestDetailsPanel();

    public RequestPanel(SimpleHttpClient simpleHttpClient) {
        this.httpClient = simpleHttpClient;
        setLayout(new BorderLayout());

        var requestConfig = new JPanel();
        requestConfig.setLayout(new BoxLayout(requestConfig, BoxLayout.Y_AXIS));
        urlPanel = new UrlPanel(this::sendRequest);
        requestConfig.add(urlPanel);
        requestConfig.add(requestDetailsPanel);

        var splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(requestConfig);
        splitPane.add(new JScrollPane(responseTextPane));

        add(splitPane);

        splitPane.setDividerLocation(DEFAULT_DIVIDER_LOCATION);
    }

    private void sendRequest() {
        urlPanel.setSendButtonEnabled(false);

        SwingWorker<Response, Void> worker = new SwingWorker<>() {
            @Override
            protected Response doInBackground() {
                try {
                    return httpClient.send(Request.builder()
                            .method(urlPanel.getHttpMethod())
                            .url(urlPanel.getUrl())
                            .body(requestDetailsPanel.getRequestBody())
                            .headers(requestDetailsPanel.getRequestHeaders())
                            .build());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    urlPanel.setSendButtonEnabled(true);
                }
            }

            @Override
            protected void done() {
                try {
                    responseTextPane.setText(get().getBody());
                } catch (InterruptedException | ExecutionException e) {
                    // TODO consider more user friendly approach
                    JOptionPane.showMessageDialog(
                            RequestPanel.this,
                            "Failed to send request",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    urlPanel.setSendButtonEnabled(true);
                }
            }
        };

        worker.execute();
    }
}
