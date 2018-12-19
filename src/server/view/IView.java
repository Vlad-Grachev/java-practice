package server.view;

import gameenv.Car;
import gameenv.GameMap;
import operations.Request;

import java.util.ArrayList;

public interface IView {
    Request getReq();
    void setOp(Request req);
    void sendId(int id);
    void sendGameMap(GameMap cars);
}
