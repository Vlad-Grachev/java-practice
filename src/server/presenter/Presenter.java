package server.presenter;

import gameenv.GameEngine;
import operations.Request;
import server.model.IServer;
import server.view.IView;

import static operations.Request.STOP;

public class Presenter implements IPresenter {
    IServer m;
    IView v;
    GameEngine gameEngine = new GameEngine();
    private static int players = 0;
    private static boolean isGameStarted = false;
    private int id = -1;

    public Presenter(IServer _m, IView _v) {
        m = _m;
        v = _v;

        start();
        m.addPresenter(this);
    }

    private void start() {
        Presenter p = this;
        new Thread() {
            @Override
            public void run() {
                Request req = null;
                while (req != STOP) {
                    req = v.getReq();
                    if (req == Request.NEW_PLAYER) {
                        gameEngine.addCar(players);
                        v.sendId(players++);
                    }
                    while (players < 4);
                    if(!isGameStarted){
                        isGameStarted = true;
                        m.startHurdleGeneration();
                    }
                    req = v.getReq();
                    if (req == Request.MOVE_LEFT) {
                        m.moveCarLeft(id);
                    }
                    if (req == Request.MOVE_RIGHT) {
                        m.moveCarLeft(id);
                    }
                    if (req == STOP) {
                        m.removePresenter(p);
                    }
                }
            }
        }.start();
    }

    public void update() {
        v.sendGameMap(m.getGameMap());
    }
}
