package gameenv;

import java.awt.*;
import java.util.ArrayList;

public class Lane extends GameObject{
    private static int laneNum = 0;
    private int laneId;
    private ArrayList<Hurdle> hurdles = new ArrayList<>();

    public Lane(){
        super(EnvProperties.laneLength, EnvProperties.laneWidth);
        laneId = laneNum++;
        setPosition(new Point(getWidth() * laneId, 0));
    }
    public int getLaneId() {
        return laneId;
    }
    public void addHurdle(Hurdle hurdle){
        hurdles.add(hurdle);
    }
    public void moveHurdlesDown(){
        for (Hurdle hurdle: hurdles){
            hurdle.moveDown();
        }
    }

    public boolean isHurdleOutfLane(){
        if(hurdles.size() == 0)
            return false;
        Hurdle h = hurdles.get(0);
        if (h.getY() >= getLength())
            return true;
        else
            return false;
    }

    public void removeHurdle(){
        hurdles.remove(0);
    }
    public ArrayList<Hurdle> getHurdles(){
        return hurdles;
    }
}
