package gameenv;

import java.awt.*;

public class Hurdle extends GameObject{
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
        getPosition().y += EnvProperties.hurdleStep;
    }
    public boolean isInFrame(){
        return (getPosition().y + 1 > EnvProperties.laneLength);
    }
}
