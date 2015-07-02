package ro.adipascu.androidcommon;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Adi Pascu on 5/6/2015.
 * Email mail@adipascu.ro
 */
@Deprecated
public class BaseFragment<A extends Activity> extends Fragment {
    private A parentActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //noinspection unchecked
        parentActivity = (A) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }

    public A getParentActivity() {
        return parentActivity;
    }
}
