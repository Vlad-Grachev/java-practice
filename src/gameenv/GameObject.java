package gameenv;

import java.awt.Point;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private Point position;
    private int length, width;

    public GameObject(int length, int width) {
        this.length = length;
        this.width = width;
        position = new Point(0, 0);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    int getX(){
        return position.x;
    }

    int getY(){
        return position.y;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
