package game.env;

public class Car extends GameObject {
    int ownerID = -1; 
    
    public Car(int x, int y, int length, int width, int ownerID){
        super(x,y,length, width);
        this.ownerID = ownerID;
    }
    public int getOwnerId(){
        return ownerID;
    }
}
