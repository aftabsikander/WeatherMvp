package com.aftabsikander.mvpgithub.utils;

import android.content.Context;
import android.content.res.Resources;

import static com.aftabsikander.mvpgithub.utils.ProjectUtils.showToast;

/**
 * Created by aftabsikander on 2/8/2018.
 */

public final class ViewUtils {

    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    /***
     * Converts Pixels to Density independent pixels (DP)
     * @param pixels Pixels value which needs to be converted into DP
     * @return returns dp value according to screen DPI
     */
    public static float convertPxToDp(float pixels) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return pixels / (densityDpi / 160f);
    }

    /***
     * Converts Density independent pixels to pixels according to screen density.
     * @param valueInDp DP value which needs to be converted into Pixels
     * @return returns pixels value according to screen DPI
     */
    public static int convertDpToPx(float valueInDp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(valueInDp * density);
    }

    /**
     * Display screen density on screen
     *
     * @param context calling context.
     * @return returns screen density value
     */
    public static float displayScreenDensity(Context context) {
        float density = context.getResources().getDisplayMetrics().density;

        if (density >= 4.0) {
            showToast(context, density + " XXXHDPI", ProjectUtils.MESSAGE_LENGTH_SHORT);
        } else if (density >= 3.0 && density < 4.0) {
            showToast(context, density + " XXHDPI", ProjectUtils.MESSAGE_LENGTH_SHORT);
        } else if (density >= 2.0 && density < 3.0) {
            showToast(context, density + " XHDPI", ProjectUtils.MESSAGE_LENGTH_SHORT);
        } else if (density >= 1.5 && density < 2.0) {
            showToast(context, density + " HDPI", ProjectUtils.MESSAGE_LENGTH_SHORT);
        } else if (density >= 1.0 && density < 1.5) {
            showToast(context, density + " MDPI", ProjectUtils.MESSAGE_LENGTH_SHORT);
        }
        return density;
    }
}
