package com.github.codeincluded.freeman.gui.components;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RequestDetailsPanel extends JTabbedPane {

    private final JEditorPane bodyEditorPane = new JEditorPane();
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final JTable headersTable = new JTable(tableModel);

    public RequestDetailsPanel() {
        addTab("Headers", createHeadersTabContent());
        addTab("Body", createBodyTabContent());

        tableModel.setColumnCount(2);
        tableModel.setColumnIdentifiers(new String[]{"Key", "Value"});
        tableModel.setRowCount(1);
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
        tableModel.addTableModelListener(event -> {
            int row = event.getFirstRow();
            boolean isUpdate = event.getType() == TableModelEvent.UPDATE;
            boolean isLastRow = (row + 1) == tableModel.getRowCount();
            if (isUpdate && isLastRow) {
                tableModel.addRow(new Object[]{"", ""});
            }
        });
        return new JScrollPane(headersTable);
    }

    private JComponent createBodyTabContent() {
        var panel = new JPanel(new BorderLayout());
        panel.add(bodyEditorPane, BorderLayout.CENTER);
        return panel;
    }
}
