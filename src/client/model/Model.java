package client.model;

import client.view.IObserver;
import gameenv.Car;
import gameenv.Hurdle;
import gameenv.PackedHurdle;
import gameenv.PackedMap;
import operations.Operation;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static gameenv.EnvProperties.maxNumPlayers;
import static operations.Operation.*;

public class Model {
    private PackedMap packedMap = null;
    private boolean gameOn = false;
    private static boolean allPlayersConnected = false;

    int port = 5676;
    InetAddress ip = null;
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private ArrayList<IObserver> list_o = new ArrayList<>();

    public int addNewUser(IObserver o, Operation op){
        addObserver(o);
        try {
            if (op == NEW_PLAYER) {
                oos.writeObject(op);
                int id = getId();
                if(id == maxNumPlayers - 1)
                    allPlayersConnected = true;
                return id;
            } else if (op == NEW_OBSERVER) {
                oos.writeObject(op);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addObserver(IObserver o){
        list_o.add(o);
    }

    public boolean isGameOn(){
        return gameOn;
    }

    private void refresh()
    {
        for (IObserver observer : list_o) {
            observer.updateView();
        }
    }

    public void createStreams(){
        if(cs != null) return;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        try {
            cs = new Socket(ip, port);
            System.out.println("Client started");

            oos = new ObjectOutputStream(cs.getOutputStream());
            ois = new ObjectInputStream(cs.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startListening() {
        new Thread(){
            @Override
            public void run() {
                try {
                    gameOn = true;
                    while(true) {
                            Operation op = (Operation) ois.readObject();
                            if (op == RECEIVE_CARS) {
                                packedMap = (PackedMap) ois.readObject();
                                refresh();
                            }
                            if (op == STOP) {
                                gameOn = false;
                                sendReq(STOP);
                                refresh();
                                break;
                            }
                        }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }}.start();
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

    public PackedMap getPackedMap() {
        return packedMap;
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

    public void sendReq(Operation req)
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
