package client.paintedobjects;

import gameenv.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PaintedCar extends JComponent {
    private Graphics2D g2d;
    private Rectangle2D rect;
    int x, y, w, l;
    Color color;

    public PaintedCar(Car car, Color color){
        x = car.getPosition().x;
        y = car.getPosition().y;
        l = car.getLength();
        w = car.getWidth();
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g){
        g2d = (Graphics2D) g;
        rect = new Rectangle2D.Double(x, y, w, l);
        g2d.setPaint(color);
        g2d.fill(rect);
    }
}
