package game.env;

public class DrivenCar extends Car {
    private String driverName;
    private int step;
    
    public DrivenCar(int x, int y, int length, 
            int width, int ownerId, int step){
        super(x,y,length, width, ownerId);
        this.step = step;
    }
    public void moveLeft(){}
    public void moveRight(){}
    boolean intersected(Hurdle h){
        // lane?
        if((y > h.y)||(y + width < h.y))
            return false;
        else
            return true;
    }
}
