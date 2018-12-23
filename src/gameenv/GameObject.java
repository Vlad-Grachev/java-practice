package gameenv;

import java.awt.Point;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private int x, y;
    private int length, width;

    public GameObject(int length, int width) {
        this.length = length;
        this.width = width;
        x =0; y = 0;
    }

    public void setPosition(Point position) {
        this.x = position.x;
        this.y = position.y;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
