package ro.adipascu.androidcommon.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ro.adipascu.androidcommon.Tools;

/**
 * Created by Adi Pascu on 7/21/2015.
 * Email mail@adipascu.ro
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = Tools.inflate(android.R.layout.simple_list_item_2, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setLocale(getLocales()[position]);
    }

    @Override
    public int getItemCount() {
        return getLocales().length;
    }

    private Locale[] getLocales() {
        return Locale.getAvailableLocales();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(android.R.id.text1)
        TextView textView1;
        @InjectView(android.R.id.text2)
        TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setLocale(Locale locale) {
            textView1.setText(locale.getDisplayLanguage());
            textView2.setText(locale.getDisplayCountry());
        }
    }
}
