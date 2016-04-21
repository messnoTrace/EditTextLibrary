package com.notrace.library.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by abc on 2016/4/21.
 */
public class DisplayUtils {
    /**
     * get screen width
     *
     * @param activity
     *
     * @return
     */
    public static int screenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * get screen height
     *
     * @param activity
     *
     * @return
     */
    public static int screenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * convert px to dip
     *
     * @param pxValue
     *
     * @return
     */
    public static float px2dip(Context context, float pxValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue, context.getResources().getDisplayMetrics());
    }

    /**
     * convert dip to pix
     *
     * @param dipValue
     *
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());

    }

    /**
     * convert sp to pix
     *
     * @param spValue
     *
     * @return
     */
    public static float sp2px(Context context, float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());

    }
}
