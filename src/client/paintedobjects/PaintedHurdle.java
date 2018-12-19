package client.paintedobjects;

import gameenv.Car;
import gameenv.Hurdle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PaintedHurdle extends JComponent {
    private Graphics2D g2d;
    private Rectangle2D rect;
    int x, y, w, l;
    Color color;

    public PaintedHurdle(Hurdle hurdle){
        x = hurdle.getPosition().x;
        y = hurdle.getPosition().y;
        l = hurdle.getLength();
        w = hurdle.getWidth();
        this.color = Color.black;
    }

    @Override
    public void paintComponent(Graphics g){
        g2d = (Graphics2D) g;
        rect = new Rectangle2D.Double(x, y, w, l);
        g2d.setPaint(color);
        g2d.fill(rect);
    }
}
