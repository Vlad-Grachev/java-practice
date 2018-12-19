package gameenv;

public class EnvProperties {
    public static int maxNumPlayers = 4;
    public static int laneLength = 800;
    public static int laneWidth = 113;
    public static int carLength = 150;
    public static int carWidth = 90;
    public static int hurdleLength = 60;
    public static int hurdleStep = 160;

    public static void setProperties(int mnp, int ll, int lw, int cl, int cw, int hl){
        maxNumPlayers = mnp;
        laneLength = ll;
        laneWidth = lw;
        carLength = cl;
        carWidth = cw;
        hurdleLength = hl;
    }
}
