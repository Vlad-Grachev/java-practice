package server.presenter;

import server.model.IServer;
import server.view.IView;

public class BPresenter {
    static public IPresenter build(IServer m, IView v){
        return new Presenter(m, v);
    }
}
