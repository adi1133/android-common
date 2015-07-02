package ro.adipascu.androidcommon.callback;

/**
 * Created by Adi Pascu on 5/13/2015.
 * Email mail@adipascu.ro
 */
public interface OnDeleteListener<T> extends OnSelectListener<T> {
    void delete(T item);
}
