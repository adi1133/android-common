package ro.adipascu.androidcommon.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Adi Pascu on 5/21/2015.
 * Email mail@adipascu.ro
 */

public abstract class FragmentAView<P extends APresenter> extends Fragment implements AView<P> {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);
        getPresenter().setView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().setView(null);
    }

    abstract protected void bind(View view);

}
