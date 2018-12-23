package gameenv;

import java.awt.*;
import java.io.Serializable;

public class Hurdle extends GameObject implements Serializable {
    public int hy;
    public Hurdle(int laneId){
        super(EnvProperties.hurdleLength, EnvProperties.laneWidth);
        setPosition(new Point(getWidth() * laneId, 0));
    }
    boolean intersects(Car car){
        boolean intersection =((getY() >= car.getY()) &&
                (getY() + getLength() <= car.getY() + car.getLength()));
        if(intersection) return intersection;
        intersection = (getY() <= car.getY()) && (getY() + getLength() > car.getY());
        if(intersection) return intersection;
        intersection = (getY() + getLength() > car.getY() + getLength())&&(getY() <= car.getY() + getLength());
        return intersection;
    }
    public void moveDown(){
        Point newPosition = getPosition();
        newPosition.y += EnvProperties.hurdleStep;
        setPosition(newPosition);
    }
    public boolean isInFrame(){
        return (getPosition().y + 1 > EnvProperties.laneLength);
    }

}
