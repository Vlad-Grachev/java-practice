package server;

import server.model.BServer;
import server.model.IServer;
import server.presenter.BPresenter;
import server.presenter.IPresenter;
import server.view.BView;
import server.view.IView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerInitialPoint {
    private int port = 5676;;
    private InetAddress ip = null;

    public void startServer(){
        IServer m = BServer.model();
        ServerSocket ss;
        Socket cs;
        try {
            ip = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            ss = new ServerSocket(port, 0, ip);
            System.out.println("Server started");
            while (true){
                cs = ss.accept();
                System.out.println("New connection");
                IView v = BView.build(cs);
                IPresenter p = BPresenter.build(m, v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerInitialPoint().startServer();
    }
}
