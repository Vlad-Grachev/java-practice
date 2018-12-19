package client.view;

import client.model.BModel;
import client.model.Model;
import client.paintedobjects.PaintedCar;
import client.paintedobjects.PaintedHurdle;
import gameenv.Car;
import gameenv.GameMap;
import gameenv.Hurdle;
import operations.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class View extends JFrame implements IObserver {
    private int id = -1;
    private View thisFrame = this;
    Model m = BModel.model();

    JPanel gameMap = null;
    ArrayList<PaintedCar> paintedCars = null;
    ArrayList<PaintedHurdle> paintedHurdles = null;
    Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};

    public View() {
        super("Racing Game");
        setSize(452, 800);
        setLocation(600, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JDialog dialog = new JDialog(this, "Choose your role");
        dialog.setSize(452, 800);
        dialog.setLocation(600, 100);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton pButton = new JButton("Driver");
        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = m.addPlayer(thisFrame);
                dialog.setVisible(false);
            }
        });
        JButton vButton = new JButton("Viewer");
        vButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.addViewer(thisFrame);
                dialog.setVisible(false);
            }
        });

        dialog.add(pButton);
        dialog.add(vButton);
        pButton.setBounds(10,10,40,40);
        vButton.setBounds(10,10,40,40);
        dialog.setVisible(true);

        addGameMap();
        //addCars();

        setVisible(true);
    }

    private void addGameMap(){
        gameMap = new JPanel();
        gameMap.setFocusable(true);
        gameMap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){}
                    m.moveCarLeft();
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){}
                m.moveCarRight();
            }
        });
        thisFrame.add(gameMap);
    }

    public void paintGameMap(GameMap gameMap){
        ArrayList<Car> cars = gameMap.getCars();
        ArrayList<Hurdle> hurdles = gameMap.getHurdles();
        if(paintedCars!=null && paintedCars.size() > 0) {
            for (PaintedCar pCar: paintedCars)
                thisFrame.remove(pCar);
        }
        paintedCars = null;
        if(cars.size() != 0) {
            paintedCars = new ArrayList<PaintedCar>();
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                if (car.getStatus() == Status.CAR_OK) {
                    paintedCars.add(new PaintedCar(car, colors[car.getOwnerId()]));
                }
            }
        }
        if(paintedHurdles!=null && paintedHurdles.size() > 0) {
            for (PaintedHurdle pHurdle: paintedHurdles)
                thisFrame.remove(pHurdle);
        }
        paintedHurdles = null;
        if(hurdles.size() != 0) {
            paintedHurdles = new ArrayList<PaintedHurdle>();
            for (int i = 0; i < hurdles.size(); i++) {
                Hurdle hurdle = hurdles.get(i);
                paintedHurdles.add(new PaintedHurdle(hurdle));
            }
        }
        for(PaintedCar car: paintedCars)
            thisFrame.add(car);

        for(PaintedHurdle hurdle: paintedHurdles)
            thisFrame.add(hurdle);
    }

    public void refresh(){
        paintGameMap(m.getGameMap());
        thisFrame.refresh();
    }
}
