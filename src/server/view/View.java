package server.view;

import gameenv.PackedMap;
import operations.Operation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class View implements IView {
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public View(Socket _cs) {
        cs = _cs;
        try {
            ois = new ObjectInputStream(cs.getInputStream());
            oos = new ObjectOutputStream(cs.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendId(int id) {
        try {
            oos.writeInt(id);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Operation getOp() {
        Operation op = null;
        try {
            op = (Operation) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return op;
    }

    public void sendGameMap(PackedMap packedMap) {
        new Thread()
        {
            @Override
            public void run() {
                try {
                    oos.writeObject(packedMap);
                    oos.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void setOp(Operation op) {
        try {
            oos.writeObject(op);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
