package com.github.codeincluded.freeman;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.github.codeincluded.freeman.gui.components.MainWindow;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    private static final String APP_NAME = "Freeman";

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeAndWait(() -> new MainWindow(APP_NAME).setVisible(true));
    }
}