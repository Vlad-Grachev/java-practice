package server.model;

import gameenv.Car;
import gameenv.GameMap;
import gameenv.Hurdle;
import gameenv.Lane;
import operations.Status;
import server.presenter.IPresenter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Server implements IServer {
    private ArrayList<IPresenter> presenters = new ArrayList<>();
    private GameMap gameMap = null;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Lane> lanes = new ArrayList<>();
    private int hurdleCount = 0;

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

    public GameMap getGameMap() {
        return gameMap;
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
            car.changeLane(lanes.get(car.getLaneId() - 1));
    }

    public void startHurdleGeneration(){
        GameTimer t = new GameTimer();
        t.startTimer();
    }

    @Override
    public void addPresenter(IPresenter p) {
        presenters.add(p);
    }

    @Override
    public void removePresenter(IPresenter p) {
        presenters.remove(p);
    }

    public void refresh() {
        ArrayList<Hurdle> hurdles = new ArrayList<>();
        for(Lane lane: lanes){
            for(Hurdle hurdle: lane.getHurdles())
                hurdles.add(hurdle);
        }
        gameMap = new GameMap(hurdles, cars);
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
                        System.out.println("New hurdle at lane: " + laneId);
                    } else {
                        System.out.println("Checking intersections");
                        for (Lane lane : lanes) {
                            lane.moveHurdlesDown();
                            if (lane.isHurdleOutfLane()) {
                                lane.removeHurdle();
                                hurdleCount--;
                            }
                        }
                        int i = 0;
                        while (i < cars.size()){
                            if(cars.get(i).getStatus() == Status.CAR_CRUSHED){
                                System.out.println("Player " + cars.get(i) + "lose");
                                cars.remove(i);
                            }
                            else
                                i++;
                        }

                    }
                    refresh();
                }
            };
        }
    }
}
