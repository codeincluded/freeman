package com.github.codeincluded.freeman.gui.components;

import com.github.codeincluded.freeman.gui.components.menu.MainMenu;
import com.github.codeincluded.freeman.net.SimpleHttpClient;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    public MainWindow(String appName) {
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(appName);

        setJMenuBar(new MainMenu());

        setContentPane(createContentPane());
    }

    private JPanel createContentPane() {
        var contentPane = new JPanel(new BorderLayout());
        contentPane.add(new RequestPanel(new SimpleHttpClient()), BorderLayout.CENTER);
        return contentPane;
    }
}
