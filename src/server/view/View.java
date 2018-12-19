package server.view;

import gameenv.Car;
import gameenv.GameMap;
import operations.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class View implements IView {
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream dos;
    private int id = -1;

    public View(Socket _cs) {
        cs = _cs;
        try {
            ois = new ObjectInputStream(cs.getInputStream());
            dos = new ObjectOutputStream(cs.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendId(int id) {
        try {
            dos.writeObject(id);
            dos.flush();
            dos.writeObject(id);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Request getReq() {
        Request req = null;
        try {
            req = (Request) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return req;
    }

    public void sendGameMap(GameMap gameMap) {
        new Thread()
        {
            @Override
            public void run() {
                try {
                    dos.writeObject(gameMap);
                    dos.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void setOp(Request req) {
        try {
            dos.writeObject(req);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
