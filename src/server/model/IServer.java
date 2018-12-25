package server.model;

import gameenv.Car;
import gameenv.PackedMap;
import server.presenter.IPresenter;

import java.util.ArrayList;

public interface IServer {
    void addPresenter(IPresenter p);
    void removePresenter(IPresenter p);
    void refresh();
    boolean isGameOn();
    void addCar(int ownerId);
    void moveCarLeft(int ownerId);
    void moveCarRight(int ownerId);
    void startHurdleGeneration();
    ArrayList<Car> getCars();
    PackedMap getPackedMap();
    PackedMap packMap();

}
