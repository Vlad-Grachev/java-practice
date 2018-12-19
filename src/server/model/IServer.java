package server.model;

import gameenv.Car;
import gameenv.GameMap;
import server.presenter.IPresenter;

import java.util.ArrayList;

public interface IServer {
    void addPresenter(IPresenter p);
    void removePresenter(IPresenter p);
    void refresh();
    void addCar(int ownerId);
    void moveCarLeft(int ownerId);
    void moveCarRight(int ownerId);
    void startHurdleGeneration();
    ArrayList<Car> getCars();
    GameMap getGameMap();

}
