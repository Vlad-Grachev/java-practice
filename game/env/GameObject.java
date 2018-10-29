package game.env;

public abstract class GameObject {
    int x, y;
    int length, width;
    
    public GameObject(int x, int y, int length, int width){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }
    protected void setPosition(int x, int y){
       this.x = x;
       this.y = y;
    }
    public int getX(){
        return x;
    };
    public int getY(){
        return y;
    }
    public int[] getPosition(){
        int[] xy = {x, y};
        return xy;
    };
    public int getLength(){
        return length;
    };
    public int getWidth(){
        return width;
    }
}
