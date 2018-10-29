package game.env;

import java.util.ArrayList;

public class GameMap {
    private ArrayList<Lane> lanes = new ArrayList<>();
    int laneNum;
    int length, width;
    
    public GameMap(int laneNum){
        for(int i = 0; i <= laneNum; i++){
            lanes.add(new Lane(i));
        }
    }
    public int getLaneNum(){
        return lanes.size();
    }
}
