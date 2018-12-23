package gameenv;

import java.io.Serializable;

public class PackedHurdle implements Serializable {
    public int x, y;

    public PackedHurdle(Hurdle h){
        x = h.getX();
        y = h.getY();
    };
}
