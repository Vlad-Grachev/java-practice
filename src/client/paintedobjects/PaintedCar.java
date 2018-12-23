package client.paintedobjects;

import gameenv.Car;
import gameenv.EnvProperties;
import gameenv.PackedCar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class PaintedCar extends JComponent implements Serializable {
    private Graphics2D g2d;
    private Rectangle2D rect;
    public int x, y, w, l;
    public Color color;

    public PaintedCar(PackedCar car, Color color){
        x = car.x;
        y = car.y;
        l = EnvProperties.carLength;
        w = EnvProperties.carWidth;
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g){
        g2d = (Graphics2D) g;
        rect = new Rectangle2D.Double(x, y, w, l);
        g2d.setPaint(color);
        g2d.fill(rect);
    }

    @Override
    public String toString() {
        return "PaintedCar{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", l=" + l +
                ", color=" + color +
                '}';
    }
}
