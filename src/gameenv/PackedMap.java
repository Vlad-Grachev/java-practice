package gameenv;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PackedMap implements Serializable {
    private ArrayList<PackedHurdle> hurdles = new ArrayList<>();
    private ArrayList<PackedCar> cars = new ArrayList<>();

    public PackedMap(ArrayList<PackedHurdle> hurdles, ArrayList<PackedCar> cars) {
        this.hurdles = hurdles;
        this.cars = cars;
    }

    public ArrayList<PackedHurdle> getHurdles() {
        return hurdles;
    }

    public ArrayList<PackedCar> getCars() {
        return cars;
    }

}
