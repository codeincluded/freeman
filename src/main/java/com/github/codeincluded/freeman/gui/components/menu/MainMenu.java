package com.github.codeincluded.freeman.gui.components.menu;

import javax.swing.*;

public class MainMenu extends JMenuBar {

    public MainMenu() {
        var fileMenu = new JMenu("File");
        var exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitMenuItem);

        this.add(fileMenu);
    }
}
