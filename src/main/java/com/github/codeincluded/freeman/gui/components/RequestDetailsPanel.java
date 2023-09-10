package com.github.codeincluded.freeman.gui.components;

import javax.swing.*;
import java.awt.*;

public class RequestDetailsPanel extends JTabbedPane {

    public RequestDetailsPanel() {
        JPanel headersPanel = createHeadersPanel();
        addTab("Headers", headersPanel);
    }

    private JPanel createHeadersPanel() {
        JTable table = new JTable(3, 2);
        JPanel headersPanel = new JPanel();
        headersPanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        int width = (int) scrollPane.getPreferredSize().getWidth();
        int height = (int) (table.getRowHeight() * table.getRowCount() +
                        table.getTableHeader().getPreferredSize().getHeight() +
                        scrollPane.getInsets().top +
                        scrollPane.getInsets().bottom);
        scrollPane.setPreferredSize(new Dimension(width, height));
        headersPanel.add(scrollPane, BorderLayout.NORTH);
        headersPanel.add(new JButton("+"), BorderLayout.CENTER);
        return headersPanel;
    }
}
