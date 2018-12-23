package client;

import client.model.BModel;
import client.view.View;

import javax.swing.*;

public class ClientInitialPoint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BModel.model().createStreams();
                new View();
            }
        });
    }
}
