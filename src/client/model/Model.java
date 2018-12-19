package client.model;

import client.view.IObserver;
import gameenv.Car;
import gameenv.GameMap;
import operations.Request;
import operations.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static operations.Request.MOVE_LEFT;
import static operations.Request.MOVE_RIGHT;

public class Model {
    private ArrayList<Car> cars = null;
    private GameMap gameMap = null;

    int port = 5676;
    InetAddress ip = null;
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private ArrayList<IObserver> list_o = new ArrayList<>();

    public int addPlayer(IObserver o)
    {
        list_o.add(o);
        return getId();
    }

    public void addViewer(IObserver o)
    {
        list_o.add(o);
    }
    private void refresh()
    {
        for (IObserver observer : list_o) {
            observer.refresh();
        }
    }

    public void init()
    {
        if(cs != null) return;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        try {

            cs = new Socket(ip, port);
            System.out.append("Client start \n");

            ois = new ObjectInputStream(cs.getInputStream());
            oos = new ObjectOutputStream(cs.getOutputStream());

            new Thread(){
                @Override
                public void run() {
                    try {
                        while(true)
                        {
                            Response resp = (Response) ois.readObject();
                            if(resp == Response.RECEIVE_CARS){
                                gameMap = (GameMap) ois.readObject();
                                cars = gameMap.getCars();
                            }
                            if(resp == Response.STOP)
                            {
                                break;
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        refresh();
    }

    private int getId() {
        int id = -1;
        try {
            id = ois.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<Car> getCars(){
        return cars;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void moveCarLeft(){
        try {
            oos.writeObject(MOVE_LEFT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveCarRight(){
        try {
            oos.writeObject(MOVE_RIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReq(Request req)
    {
        if(cs == null) return;
        try {
            oos.writeObject(req);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
