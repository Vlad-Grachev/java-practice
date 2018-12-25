package server.presenter;

import static gameenv.EnvProperties.maxNumPlayers;
import static operations.Operation.*;
import operations.Operation;
import server.model.IServer;
import server.view.IView;

public class Presenter implements IPresenter {
    IServer m;
    IView v;
    private static int players = 0;
    private static boolean gameStarted = false;
    private int id = -1;

    public Presenter(IServer _m, IView _v) {
        m = _m;
        v = _v;
        m.addPresenter(this);
        start();
    }

    private void start() {
        Presenter p = this;
        new Thread() {
            @Override
            public void run() {
                Operation op = null;
                while (players < maxNumPlayers){
                    op = v.getOp();
                    if (op == NEW_PLAYER) {
                        id = players++;
                        m.addCar(id);
                        v.sendId(id);
                    }
                }
                if(!gameStarted){
                    gameStarted = true;
                    m.startHurdleGeneration();
                }
                while (op != STOP) {
                    op = v.getOp();
                    if (op == MOVE_LEFT) {
                        m.moveCarLeft(id);
                    }
                    if (op == MOVE_RIGHT) {
                        m.moveCarRight(id);
                    }
                    if (op == STOP) {
                        System.out.println("Client disconnected");
                        m.removePresenter(p);
                    }
                }
            }
        }.start();
    }

    public void update() {
        if(m.isGameOn()){
            v.setOp(RECEIVE_CARS);
            v.sendGameMap(m.getPackedMap());
        } else {
            v.setOp(STOP);
        }
    }
}
