package server.model;

public class BServer {
    static IServer m = new Server();
    public static IServer model(){
        return m;
    }
}
