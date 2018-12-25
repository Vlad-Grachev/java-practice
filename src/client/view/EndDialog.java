package client.view;

import operations.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndDialog extends JDialog {
    public EndDialog(JFrame owner) {
        super(owner, "Choose your role");
        setSize(340, 600);
        setLocation(600, 100);
        setResizable(false);
        setModal(true);
        setLayout(new GridBagLayout());
        addButtons();
        setVisible(true);
    }

    private void addButtons() {
        JLabel space = new JLabel("Game over");
        add(space, new GridBagConstraints());
    }
}
