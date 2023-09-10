package com.github.codeincluded.freeman.gui.components;

import com.github.codeincluded.freeman.data.Request;
import com.github.codeincluded.freeman.data.Response;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

public class UrlPanel extends JPanel {

    private final JTextField urlInput = new JTextField("https://google.com");
    private final JComboBox<String> methodSelect = new JComboBox<>(new String[]{"GET"});

    public UrlPanel(
            Function<Request, Response> requestSender,
            Consumer<Response> onSuccess,
            Consumer<Throwable> onError
    ) {
        setLayout(new BorderLayout());
        add(methodSelect, BorderLayout.WEST);
        add(urlInput, BorderLayout.CENTER);
        var sendButton = createSendButton(requestSender, onSuccess, onError);
        add(sendButton, BorderLayout.EAST);
    }

    private JButton createSendButton(
            Function<Request, Response> requestSender,
            Consumer<Response> onSuccess,
            Consumer<Throwable> onError
    ) {
        var sendButton = new JButton("Send");
        sendButton.addActionListener(event -> {
            sendButton.setEnabled(false);

            Request request = Request.builder()
                    .url(urlInput.getText())
                    .method((String) methodSelect.getSelectedItem())
                    .build();

            SwingWorker<Response, Void> worker = new SwingWorker<>() {
                @Override
                protected Response doInBackground() {
                    try {
                        return Response.builder().body(requestSender.apply(request).getBody()).build();
                    } catch (Exception e) {
                        onError.accept(e);
                        throw e;
                    } finally {
                        sendButton.setEnabled(true);
                    }
                }

                @Override
                protected void done() {
                    try {
                        onSuccess.accept(get());
                    } catch (InterruptedException | ExecutionException e) {
                        // ignored
                    } finally {
                        sendButton.setEnabled(true);
                    }
                }
            };

            worker.execute();
        });
        return sendButton;
    }
}
