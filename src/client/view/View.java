package client.view;

import client.model.BModel;
import client.model.Model;
import client.paintedobjects.PaintedMap;
import operations.Operation;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JFrame implements IObserver {
    private int id = -1;
    private View thisFrame = this;
    private Model m = BModel.model();
    private Operation userType = null;
    private PaintedMap paintedMap = null;

    public View() {
        super("Racing Game");
        setSize(340, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        StartDialog startDialog = new StartDialog(this);
        userType = startDialog.getUserType();
        setLocation(startDialog.getLocation());
        startDialog.dispose();
        if(userType == null)
            System.exit(0);
        System.out.println(id = m.addNewUser(this, userType));
        if(id != -1)
            addKeyListeners();
        m.startListening();
        setVisible(true);
    }

    private void addKeyListeners(){
        thisFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    m.moveCarLeft();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    m.moveCarRight();
                }
            }
        });
    }

    @Override
    public void updateView(){
        if(paintedMap != null) {
            if(m.isGameOn()){
                paintedMap.setMap(m.getPackedMap());
            } else {
                endGame();
            }
        }
        else {
            paintedMap = new PaintedMap(m.getPackedMap());
            add(paintedMap);
            setVisible(true);
        }
    }

    public void endGame(){
        setVisible(false);
        new EndDialog(this);
        System.exit(0);
    }
}
