package client.paintedobjects;

import gameenv.*;
import operations.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PaintedMap extends JComponent {
    private Graphics2D g2d;
    private Rectangle2D rect;

    private Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
    ArrayList<PaintedCar> pCars = null;
    ArrayList<PaintedHurdle> pHurdles = null;

    public PaintedMap(PackedMap packedMap){
        setMap(packedMap);
    }

    public void setMap(PackedMap packedMap){
        ArrayList<PackedCar> cars = packedMap.getCars();
        ArrayList<PackedHurdle> hurdles = packedMap.getHurdles();
        if(cars.size() != 0) {
            pCars = new ArrayList<PaintedCar>();
            for (int i = 0; i < cars.size(); i++) {
                PackedCar car = cars.get(i);
                if (car.status == Status.CAR_OK) {
                    PaintedCar pc = new PaintedCar(car, colors[car.ownerId]);
                    pCars.add(pc);
                }
            }
        }
        if(hurdles.size() != 0) {
            pHurdles = new ArrayList<PaintedHurdle>();
            for (PackedHurdle hurdle: hurdles) {
                pHurdles.add(new PaintedHurdle(hurdle));
            }
        }
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        g2d = (Graphics2D) g;
        if(pCars != null){
            for(PaintedCar car: pCars){
                rect = new Rectangle2D.Double(car.x, car.y, car.w, car.l);
                g2d.setPaint(car.color);
                g2d.fill(rect);
            }
        }
        if(pHurdles != null){
            for(PaintedHurdle hurdle: pHurdles){
                rect = new Rectangle2D.Double(hurdle.x, hurdle.y, hurdle.w, hurdle.l);
                g2d.setPaint(hurdle.color);
                g2d.fill(rect);
            }
        }
    }
}
