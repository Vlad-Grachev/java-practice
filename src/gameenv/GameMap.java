package gameenv;

import java.io.Serializable;
import java.util.ArrayList;

public class GameMap implements Serializable {
    private ArrayList<Hurdle> hurdles = null;
    private ArrayList<Car> cars = null;

    public GameMap(ArrayList<Hurdle> hurdles, ArrayList<Car> cars) {
        this.hurdles = hurdles;
        this.cars = cars;
    }

    public ArrayList<Hurdle> getHurdles() {
        return hurdles;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
