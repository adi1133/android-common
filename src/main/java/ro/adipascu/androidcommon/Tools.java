package ro.adipascu.androidcommon;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.SearchView;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Adi Pascu on 4/30/2015.
 * Email mail@adipascu.ro
 */
public class Tools {

    //todo: check if this method works
    public static void dismissKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null)
            dismissKeyboard(view);
    }

    public static void dismissKeyboard(EditText editText) {
        dismissKeyboard((View) editText);
    }

    private static void dismissKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static void visible(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public static void toast(Context context, @StringRes int string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, @StringRes int string) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
    }


    public static String text(EditText nameEdit) {
        return nameEdit.getText().toString();
    }

    public static void error(TextView textView, @StringRes int string) {
        textView.setError(textView.getResources().getString(string));
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void focus(EditText editText) {
        editText.setSelection(editText.getText().length());
        editText.requestFocus();
        Context context = editText.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void color(ImageView imageView, @ColorRes int colorRes) {
        imageView.setBackgroundColor(imageView.getResources().getColor(colorRes));

    }

    public static String text(SearchView searchView) {
        return searchView.getQuery().toString();
    }

    public static void time(TextView textView, Date date) {
        java.text.DateFormat dateFormat = DateFormat.getTimeFormat(textView.getContext());
        textView.setText(dateFormat.format(date));
    }

    public static void date(TextView textView, Date date) {
        java.text.DateFormat dateFormat = DateFormat.getDateFormat(textView.getContext());
        textView.setText(dateFormat.format(date));
    }

    public static View inflate(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    public static boolean isEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static int getActionBarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int ret = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return ret;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void text(TextView textView, @StringRes int str, Object... formatArgs) {
        Resources res = textView.getResources();
        textView.setText(res.getString(str, formatArgs));
    }

    public static String time(Context context, Date date) {
        java.text.DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        return timeFormat.format(date);
    }

    public static String date(Context context, Date date) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        return dateFormat.format(date);
    }

    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }
}
