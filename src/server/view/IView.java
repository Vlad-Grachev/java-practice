package server.view;

import gameenv.PackedMap;
import operations.Operation;

public interface IView {
    Operation getOp();
    void setOp(Operation op);
    void sendId(int id);
    void sendGameMap(PackedMap cars);
}
