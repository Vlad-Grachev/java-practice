package game.env;

import java.util.ArrayList;

public class Lane {
    private ArrayList<Car> cars = new ArrayList<>();
    private int laneId;
    private int length, width;
    
    public Lane(int laneId){
        this.laneId = laneId;
    }
    public void addCar(DrivenCar dCar){
        cars.add(dCar);
    }
    public boolean hasCar(Car car){
        for(Car listCar: cars){
            if(listCar.getOwnerId() == car.getOwnerId())
                return true;
        }
        return false;
    }
    public void removeCar(Car car){
        cars.remove(car);
    }
    public ArrayList<Car> getCars(){
        return cars;
    }

    public int getLaneId() {
        return laneId;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
    
}
