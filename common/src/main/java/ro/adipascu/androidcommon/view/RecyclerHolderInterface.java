package ro.adipascu.androidcommon.view;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Adrian Pascu on 16.11.2015.
 * mail@adipascu.ro
 */
interface RecyclerHolderInterface {
    void setEmptyText(@StringRes int emptyText);

    RecyclerView getRecyclerView();

    RecyclerView.Adapter getAdapter();

    void setAdapter(RecyclerView.Adapter adapter);

    void setLayoutManager(RecyclerView.LayoutManager layoutManager);
}
