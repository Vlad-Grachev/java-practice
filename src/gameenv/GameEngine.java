package gameenv;

import operations.Status;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Lane> lanes = new ArrayList<>();
    private int hurdleCount = 0;

    public GameEngine(){
        for (int i = 0; i < 4; i++)
            lanes.add(new Lane());
    }

    public void addCar(int ownerId){
        cars.add(new Car(lanes.get(ownerId), ownerId));
    }

    public void removeCar(Car car){
        cars.remove(car);
    }

    public void moveCarLeft(int ownerId){
        Car car = cars.get(ownerId);
        if(car.getLaneId() - 1 >= 0)
            car.changeLane(lanes.get(car.getLaneId() - 1));
    }

    public void moveCarRight(int ownerId){
        Car car = cars.get(ownerId);
        if(car.getLaneId() + 1 < 4)
            car.changeLane(lanes.get(car.getLaneId() - 1));
    }

    public void startGame(){
        GameTimer t = new GameTimer();
        t.startTimer();
    }

    public class GameTimer{
        private Random random = new Random();
        private Timer timer;
        public void startTimer(){
            timer = new Timer(2000, getHurdleGenerator());
            timer.start();
            while(cars.size() != 0);
            System.exit(0);
        }
        private ActionListener getHurdleGenerator(){
            return e -> {
                if (hurdleCount == 0){
                    int laneId = random.nextInt(4);
                    hurdleCount++;
                    lanes.get(laneId).addHurdle(new Hurdle(laneId));
                    System.out.println("hi1");
                }
                else {
                    System.out.println(cars.size());
                    for (Lane lane: lanes){
                        lane.moveHurdlesDown();
                        if(lane.isHurdleOutfLane()) {
                            lane.removeHurdle();
                            hurdleCount--;
                        }
                    }
                    for (Car car: cars){
                        if(car.checkStatus() == Status.CAR_CRUSHED)
                            System.out.println("Player " + car.getOwnerId() + " lose");
                    }
                    if(cars.size() != 0) {
                        for (int i = cars.size() - 1; i > 0; i--) {
                            if (cars.get(i).getStatus() == Status.CAR_CRUSHED)
                                cars.remove(i);
                        }
                    }
                    else
                        System.exit(0);
                }
            };
        }
    }

    public static void main(String[] args) {
        GameEngine gm = new GameEngine();
        for (int i = 0; i < 4; i++)
            gm.addCar(i);
        gm.startGame();
    }
}
