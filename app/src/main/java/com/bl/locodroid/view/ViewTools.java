package com.bl.locodroid.view;

import android.support.annotation.IdRes;
import android.widget.TextView;

/**
 * Created by nmalesic on 28/02/2016.
 */
public class ViewTools {
    public static void setTextViewWithEmpty(TextView txv, String text) {
        if (text.isEmpty()) {
            txv.setHeight(0);
        } else {
            txv.setText(text);
        }
    }
}
