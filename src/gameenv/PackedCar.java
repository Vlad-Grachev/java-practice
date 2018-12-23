package gameenv;

import operations.Status;

import java.io.Serializable;

public class PackedCar implements Serializable {
    public int x, y, ownerId;
    public Status status;

    public PackedCar(Car c){
        x = c.getX();
        y = c.getY();
        ownerId = c.getOwnerId();
        status = c.getStatus();
    };
}
