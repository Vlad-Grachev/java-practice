package server.model;

import gameenv.*;
import operations.Status;
import server.presenter.IPresenter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Server implements IServer {
    private ArrayList<IPresenter> presenters = new ArrayList<>();
    private PackedMap packedMap = null;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Lane> lanes = new ArrayList<>();
    private int hurdleCount = 0;
    private boolean gameOn = false;

    public Server() {
        for (int i = 0; i < 4; i++)
            lanes.add(new Lane());
    }

    public void addCar(int ownerId) {
        cars.add(new Car(lanes.get(ownerId), ownerId));
    }

    public ArrayList<Car> getCars(){
        return cars;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public PackedMap getPackedMap() {
        return packedMap;
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void moveCarLeft(int ownerId) {
        Car car = cars.get(ownerId);
        if (car.getLaneId() - 1 >= 0)
            car.changeLane(lanes.get(car.getLaneId() - 1));
    }

    public void moveCarRight(int ownerId) {
        Car car = cars.get(ownerId);
        if (car.getLaneId() + 1 < 4)
            car.changeLane(lanes.get(car.getLaneId() + 1));
    }

    public void startHurdleGeneration(){
        GameTimer t = new GameTimer();
        t.startTimer();
        gameOn = true;
    }

    @Override
    public void addPresenter(IPresenter p) {
        presenters.add(p);
    }

    @Override
    public void removePresenter(IPresenter p) {
        presenters.remove(p);
    }

    public PackedMap packMap(){
        ArrayList<PackedHurdle> packedHurdles = new ArrayList<>();
        for(Lane lane: lanes){
            for(Hurdle hurdle: lane.getHurdles()) {
                packedHurdles.add(new PackedHurdle(hurdle));
            }
        }
        ArrayList<PackedCar> packedCars = new ArrayList<>();
        for(Car car: cars){
            packedCars.add(new PackedCar(car));
        }
        packedMap = new PackedMap(packedHurdles, packedCars);
        return packedMap;
    }

    public void refresh() {
        packMap();
        for (IPresenter presenter : presenters) {
            presenter.update();
        }
    }

    public class GameTimer {
        private Random random = new Random();
        private Timer timer;

        public void startTimer() {
            timer = new Timer(1800, getHurdleGenerator());
            timer.start();
        }

        private ActionListener getHurdleGenerator() {
            return e -> {
                if(cars.size() > 0) {
                    if (hurdleCount == 0) {
                        int laneId = random.nextInt(4);
                        hurdleCount++;
                        lanes.get(laneId).addHurdle(new Hurdle(laneId));
                    } else {
                        for (Lane lane : lanes) {
                            lane.moveHurdlesDown();
                            if (lane.isHurdleOutOfLane()) {
                                lane.removeHurdle();
                                hurdleCount--;
                            }
                        }
                        int i = 0;
                        while (i < cars.size()){
                            if(cars.get(i).checkStatus() == Status.CAR_CRUSHED){
                                //System.out.println("Player " + cars.get(i).getOwnerId() + " lose");
                                cars.remove(i);
                            }
                            else
                                i++;
                        }
                    }
                } else {
                    gameOn = false;
                    timer.stop();
                }
                refresh();
            };
        }
    }
}
