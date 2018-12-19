package server.view;

import java.net.Socket;

public class BView {
    public static IView build(Socket s){
        return new View(s);
    }
}
