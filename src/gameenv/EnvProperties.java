package gameenv;

public class EnvProperties {
        public static int maxNumPlayers = 1;
        public static int laneLength = 600;
        public static int laneWidth = 85;
        public static int carLength = 113;
        public static int carWidth = 68;
        public static int hurdleLength = 45;
        public static int hurdleStep = 120;

    public static void setProperties(int mnp, int ll, int lw, int cl, int cw, int hl){
        maxNumPlayers = mnp;
        laneLength = ll;
        laneWidth = lw;
        carLength = cl;
        carWidth = cw;
        hurdleLength = hl;
    }
}
