package game.env;

public class Hurdle extends GameObject{
    int step = 0;
    
    public Hurdle(int x, int y, int length, int width, int step){
        super(x, y, length, width);
        this.step = step;
    }
    public void moveDown(){
        super.setPosition(x, y + step);
    }
}
