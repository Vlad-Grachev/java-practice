package client;

import client.view.View;

import javax.swing.*;

public class ClientInitialPoint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View();
            }
        });
    }
}
