package com.github.codeincluded.freeman.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RequestDetailsPanel extends JTabbedPane {

    private final JEditorPane bodyEditorPane = new JEditorPane();
    private final JTable headersTable = new JTable(1, 2);

    public RequestDetailsPanel() {
        addTab("Headers", createHeadersTabContent());
        addTab("Body", createBodyTabContent());
    }

    public String getRequestBody() {
        return bodyEditorPane.getText();
    }

    public Map<String, String> getRequestHeaders() {
        Map<String, String> headers = new HashMap<>();
        int rowCount = headersTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String key = (String) headersTable.getValueAt(i, 0);
            String value = (String) headersTable.getValueAt(i, 1);
            if (key != null && !key.isBlank()) {
                headers.put(key, value);
            }
        }
        return headers;
    }

    private JComponent createHeadersTabContent() {
        return new JScrollPane(headersTable);
    }

    private JComponent createBodyTabContent() {
        var panel = new JPanel(new BorderLayout());
        panel.add(bodyEditorPane, BorderLayout.CENTER);
        return panel;
    }
}
