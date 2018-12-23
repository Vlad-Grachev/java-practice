package client.view;

import operations.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartDialog extends JDialog {
    Operation userType = null;
    public StartDialog(JFrame owner){
        super(owner, "Choose your role");
        setSize(340, 600);
        setLocation(600, 100);
        setResizable(false);
        setModal(true);
        setLayout(new GridBagLayout());
        addButtons();
        setVisible(true);
    }
    private void addButtons(){
        JButton pButton = new JButton("Player");
        pButton.setSize(80, 40);
        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = Operation.NEW_PLAYER;
                setVisible(false);
            }
        });
        JLabel space = new JLabel("   ");
        JButton vButton = new JButton("Viewer");
        vButton.setSize(120, 60);
        vButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = Operation.NEW_OBSERVER;
                setVisible(false);
            }
        });
        add(pButton, new GridBagConstraints());
        add(space, new GridBagConstraints());
        add(vButton, new GridBagConstraints());
    }

    public Operation getUserType(){
        return userType;
    }
}
