package ro.adipascu.androidcommon;

/**
 * Created by Adi Pascu on 4/27/15.
 * Email mail@adipascu.ro
 */
//public class APresenter<V extends AView> {
public class APresenter<V> {
    protected V view;

    public void attach(V v) {
        this.view = v;
    }

    public void detach() {
        this.view = null;
    }

}
