package gameenv;

import operations.Status;

import java.awt.*;
import java.io.Serializable;

public class Car extends GameObject implements Serializable {
    private Lane lane;
    private int ownerId;
    private Status carStatus = Status.CAR_OK;
    public Car(Lane l, int ownerId){
        super(EnvProperties.carLength, EnvProperties.carWidth);
        lane = l;
        this.ownerId = ownerId;
        setPosition(getPositionByLane(lane));
    }

    private Point getPositionByLane(Lane lane){
        return new Point(
                lane.getX() + lane.getWidth() / 2 - getWidth() / 2,
                lane.getLength() - getLength() - getLength() / 2
        );
    }

    public void changeLane(Lane l){
        lane = l;
        setPosition(getPositionByLane(lane));
    }

    public int getLaneId(){
        return lane.getLaneId();
    }

    public Status checkStatus(){
        boolean intersection = false;
        if(lane.getHurdles().size() != 0) {
            for (Hurdle h : lane.getHurdles()) {
                intersection = intersection || h.intersects(this);
            }
        }
        if(intersection)
            setCrushed();
        return carStatus;
    }

    public void setCrushed(){
        carStatus = Status.CAR_CRUSHED;
    }

    public Status getStatus(){
        return carStatus;
    }

    public int getOwnerId(){
        return ownerId;
    }
}
