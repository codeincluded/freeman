package com.github.codeincluded.freeman.gui.components;

import javax.swing.*;
import java.awt.*;

public class UrlPanel extends JPanel {

    private final JTextField urlInput = new JTextField("https://google.com");
    private final JComboBox<String> methodSelect = new JComboBox<>(
            new String[]{"GET", "POST", "PUT", "DELETE"} // TODO add the rest
    );
    private final JButton sendButton = new JButton("Send");

    public UrlPanel(Runnable onClick) {
        setLayout(new BorderLayout());
        add(methodSelect, BorderLayout.WEST);
        add(urlInput, BorderLayout.CENTER);
        var sendButton = createSendButton(onClick);
        add(sendButton, BorderLayout.EAST);
    }

    private JButton createSendButton(Runnable onClick) {
        sendButton.addActionListener(event -> onClick.run());
        return sendButton;
    }

    public void setSendButtonEnabled(boolean enabled) {
        sendButton.setEnabled(enabled);
    }

    public String getUrl() {
        return urlInput.getText();
    }

    public String getHttpMethod() {
        return (String) methodSelect.getSelectedItem();
    }
}
