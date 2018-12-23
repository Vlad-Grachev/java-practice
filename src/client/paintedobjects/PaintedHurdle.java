package client.paintedobjects;

import gameenv.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PaintedHurdle extends JComponent {
    private Graphics2D g2d;
    private Rectangle2D rect;
    public int x, y, w, l;
    public Color color;

    public PaintedHurdle(PackedHurdle hurdle){
        x = hurdle.x;
        y = hurdle.y;
        l = EnvProperties.hurdleLength;
        w = EnvProperties.laneWidth;
        this.color = Color.black;
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
        return "PaintedHurdle{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", l=" + l +
                ", color=" + color +
                '}';
    }
}
