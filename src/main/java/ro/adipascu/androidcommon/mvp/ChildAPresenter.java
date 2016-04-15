package ro.adipascu.androidcommon.mvp;

import android.support.v4.app.Fragment;

/**
 * Created by Adi Pascu on 5/21/2015.
 * Email mail@adipascu.ro
 */
public class ChildAPresenter<V extends FragmentAView, P extends APresenter> extends APresenter<V> {


    protected P parentPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(V v) {
        super.onAttach(v);
        AView<P> parentView;
        Fragment parentFragment = view.getParentFragment();
        if (parentFragment != null)
            parentView = (AView<P>) parentFragment;
        else
            parentView = (AView<P>) view.getActivity();
        parentPresenter = parentView.getPresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentPresenter = null;
    }

    public P getParentPresenter() {
        return parentPresenter;
    }
}
