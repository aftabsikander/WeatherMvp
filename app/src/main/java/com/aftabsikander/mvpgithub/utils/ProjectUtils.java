package com.aftabsikander.mvpgithub.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.Settings;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aftabsikander.mvpgithub.R;
import com.aftabsikander.mvpgithub.ui.MainActivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import timber.log.Timber;

/**
 * Created by aftabsikander on 2/16/2018.
 */

public final class ProjectUtils {

    private ProjectUtils() {

    }

    /**
     * Show the text notification for a period of 5 seconds time.
     *
     * @see #showToast(Context, int, int)
     */
    public static final int MESSAGE_LENGTH_EXTRA_LONG = 5000;

    /**
     * Show the view or text notification for a long period of time.  This time
     * could be user-definable.
     *
     * @see Toast#setDuration
     */
    public static final int MESSAGE_LENGTH_LONG = Toast.LENGTH_LONG;
    /**
     * Show the view or text notification for a short period of time.  This time
     * could be user-definable.  This is the default.
     *
     * @see Toast#setDuration
     */
    public static final int MESSAGE_LENGTH_SHORT = Toast.LENGTH_SHORT;

    /***
     * @see Intent#FLAG_ACTIVITY_NO_ANIMATION
     */
    private static final int NO_ANIMATION = Intent.FLAG_ACTIVITY_NO_ANIMATION;

    //region Helper methods for CustomChromeTabs Implementation

    /***
     * Used to go across the application i.e start Web URL's
     * @param context the calling context.
     * @param className {@link Class} object name which is used to launch {@link Activity}
     * @param dataPassingBundle {@link Bundle} object which contains key value pairs which would
     *                                        be passed to opening activity
     */

    public static void openCustomChromeTabActivity(Context context, Class<?> className,
                                                   Bundle dataPassingBundle) {
        Intent intent = new Intent();
        intent.setClass(context, className);
        if (dataPassingBundle != null) intent.putExtras(dataPassingBundle);
        context.startActivity(intent);
    }
    //endregion

    //region Helper utility methods for SnackBar Implementation

    /**
     * Used to show simple SnackBar in which just view and msg will be display
     *
     * @param view {@link View} parent view where SnackBar would appear.
     * @param msg  Resource ID for message body
     */
    public static void showSimpleSnackBar(View view, int msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /***
     * Used to show simple SnackBar in which just view and msg will be display
     *
     * @param view {@link View} parent view where SnackBar would appear.
     * @param msg String message for {@link Snackbar}
     */
    public static void showSimpleSnackBar(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    /***
     * Used to show snackBar with ActionButton in which view,msg & button
     * will be display
     *
     * @param view {@link View} parent view where SnackBar would appear.
     * @param msg  Resource ID for message body
     * @param actionButtonText  Resource ID for action Button
     */
    public static void showSnackBarWithActionButton(View view, int msg, int actionButtonText) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(actionButtonText, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.show();
    }

    /***
     * Used to show custom snackBar in which you can change the color of message
     * text color & actionButton text color
     *
     * @param view  {@link View} parent view where SnackBar would appear.
     * @param msg Resource ID for message body
     * @param actionButtonText Resource ID for action Button
     * @param snackBarMessageTextColor Color resource id for message text color
     * @param snackBarActionButtonTextColor Color resource id for action Button text color
     */
    public static void showCustomSnackBar(View view, int msg, int actionButtonText,
                                          int snackBarMessageTextColor,
                                          int snackBarActionButtonTextColor) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(actionButtonText, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        // Changing message text color
        snackbar.setActionTextColor(snackBarMessageTextColor);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(snackBarActionButtonTextColor);
        snackbar.show();
    }
    //endregion

    //region Helper method for Checking Device Memory

    /***
     * Get device Total RAM
     *
     * @return return in byte
     */
    private static double getTotalMemory() {

        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        double initialMemory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }
            //total Memory
            //initialMemory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            initialMemory = Integer.valueOf(arrayOfString[1]);
            localBufferedReader.close();
            return initialMemory;
        } catch (IOException e) {
            return -1;
        }
    }

    /***
     * Used to calculate Device total RAM
     *
     * @param mContext the calling context.
     * @return RAM spec is returned in readable format
     */
    public static double calculateDeviceTotalRAM(Context mContext) {
        //Pre API 16
        double totalDeviceRam = 0;
        ActivityManager actManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        totalDeviceRam = memInfo.totalMem;

        String convertedValue = Double.toString(totalDeviceRam);
        if (convertedValue.contains("E")) {
            convertedValue = BigDecimal.valueOf(totalDeviceRam).toPlainString();
        } else {
            convertedValue = Integer.toString(Double.valueOf(totalDeviceRam).intValue());
        }
        long finalValue = Long.valueOf(convertedValue);

        return totalDeviceRam;
    }


    /***
     * Used to split the number from GB Unit
     *
     * @param mContext the calling context.
     * @param ramValue value to convert to readable GB unit
     * @return Returns the number from GB unit for further manipulation
     */
    public static double calculateForGBMemory(Context mContext, double ramValue) {
        String convertedValue = Double.toString(ramValue);
        if (convertedValue.contains("E")) {
            convertedValue = BigDecimal.valueOf(ramValue).toPlainString();
        } else {
            convertedValue = Integer.toString(Double.valueOf(ramValue).intValue());
        }
        long finalValue = Long.valueOf(convertedValue);
        String lastValue = "";
        String result = "";
        lastValue = Formatter.formatFileSize(mContext, finalValue);
        if (lastValue.contains("GB")) {
            int indexForGB = lastValue.indexOf("GB");
            result = lastValue.substring(0, indexForGB);
            return Double.valueOf(result.trim());
        }

        return 0;
    }

    /***
     * Used to split the number from MB Unit
     *
     * @param mContext the calling context.
     * @param ramValue value to convert to readable MB unit
     * @return Returns the number from MB unit for further manipulation
     */
    public static double calculateForMBMemory(Context mContext, double ramValue) {
        String convertedValue = Double.toString(ramValue);
        if (convertedValue.contains("E")) {
            convertedValue = BigDecimal.valueOf(ramValue).toPlainString();
        } else {
            convertedValue = Integer.toString(Double.valueOf(ramValue).intValue());
        }
        long finalValue = Long.valueOf(convertedValue);
        String lastValue = "";
        String result = "";
        lastValue = Formatter.formatFileSize(mContext, finalValue);
        if (lastValue.contains("MB")) {
            int indexForMB = lastValue.indexOf("MB");
            result = lastValue.substring(0, indexForMB);
            return Double.valueOf(result.trim());
        }

        return 0;
    }

    //endregion

    // region Internet Helper Methods

    /***
     * Used to check for network connection if the user is connected to WI-FI
     * or network mobile we can access Network related method
     *
     * @param context the calling context.
     * @return {@code true} if WI-FI or Mobile net is on return true Else false;
     */
    public static Boolean checkNetworkStatus(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        /*final NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (activeNetwork != null && wifi.isAvailable() && wifi.isConnected()) ||
        (mobile!=null && mobile.isAvailable() && mobile.isConnected());*/

    }

    // endregion

    // region General Helper Methods

    /***
     * Generate ProgressDailog for display on screen
     * @param context requires calling context
     * @return ProgressDialog instance
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /****
     * Check GPS setting is enabled or not.
     * @param context the calling context.
     * @return {@code true} return true if Location mode is set to High in setting
     */
    public static boolean checkHighAccuracyLocationMode(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Equal or higher than API 19/KitKat
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(),
                        Settings.Secure.LOCATION_MODE);
                if (locationMode == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY
                        || locationMode == Settings.Secure.LOCATION_MODE_BATTERY_SAVING) {
                    return true;
                }
            } catch (Settings.SettingNotFoundException e) {
                Timber.e(e);
            }
        } else {
            //Lower than API 19
            locationProviders = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

            if (locationProviders.contains(LocationManager.GPS_PROVIDER)
                    && locationProviders.contains(LocationManager.NETWORK_PROVIDER)) {
                return true;
            }
        }
        return false;
    }

    /***
     *  Force MediaScanner to update its media database for recently added files.
     * @param context the calling context.
     * @param filePathForScan File path which will request the media scanner to rescan and add it
     *                       to the media database.
     */
    public static void forceUpdateMediaScanner(Context context, String filePathForScan) {
        if (context != null) {
            new SingleMediaScanner(context, new File(filePathForScan));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + filePathForScan)));
            } else {
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                        Uri.parse("file://" + filePathForScan)));
            }
        }
    }

    /**
     * Used to remove the overDraw from the layout
     * We set background to null
     * To increase the performance we avoid orverDraw
     *
     * @param activity the {@link Activity} in which over draw should be removed.
     */
    //region Helper method to Remove the overdrawn.
    public static void removeOverDrawFromLayout(Activity activity) {
        activity.getWindow().setBackgroundDrawable(null);
    }
    //endregion


    /***
     * Converts long value into Integer using safe method.
     * @param l long value which needs to be autoboxed.
     * @return return int value
     */
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }


    /***
     * Makes a "deep clone" of any Java object it is given.
     * @param object Object which needs to be deep cloned
     * @return returns deep clone object
     */
    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            Timber.e(e);
            return null;
        }
    }


    /***
     * Used to open website in Mobile browser
     *
     * @param mContext the calling context.
     * @param url URL which needs to be open.
     */
    public static void openWebSiteBrowser(Context mContext, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (browserIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(browserIntent);
        }

    }

    /***
     * Used to get random string for image File name
     *
     * @param length Length for the file character to choose from
     * @return return the random string generated
     */
    public static String getRandomFileName(final int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
        //return "Picture-" + buf.toString() + ".jpg";
    }

    /***
     * Used to get random string for image File name
     * @return return the random string generated
     */
    public static String generateFileNameForImage() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.US).format(new Date());
        return "AAP_" + timeStamp + ".jpg";
    }

    /***
     * Used to get random string for Video File name
     * @return return the random string generated
     */
    public static String generateFileNameForMedia() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.US).format(new Date());
        return "AAP_" + timeStamp + ".mp4";
    }

    /***
     * Opens PlayStore App or webView to show user update notification
     * @param context the calling context
     */
    public static void openPlayStoreForUpdate(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    /***
     *  Set text in {@link ClipboardManager} PrimaryClip for user ease.
     * @param context the calling context
     * @param clipBoardData Text which needs to be saved inside {@link ClipboardManager}
     */
    public static void setClipboard(Context context, String clipBoardData) {
        ClipboardManager clipBoard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("label", clipBoardData);
        clipBoard.setPrimaryClip(data);
    }

    /**
     * Clear {@link ClipboardManager} Primary Clip text
     *
     * @param context the calling context
     */
    public static void clearClipboard(Context context) {
        ClipboardManager clipBoard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("", "");
        clipBoard.setPrimaryClip(data);
    }

    /***
     * Open {@link Activity} using {@link Class} value, provides additional functionality for
     * passing {@link Bundle} data as well
     * @param context the calling context
     * @param className activity class name which needs to be opened.
     * @param passingBundle {@link Bundle} object which contains key value pairs which would
     * be passed to opening activity
     *
     * @see  Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK
     */
    public static void genericIntent(Context context, Class<?> className,
                                     Bundle passingBundle) {
        Intent intent = new Intent(context, className);
        if (passingBundle != null) {
            intent.putExtras(passingBundle);
            if (passingBundle.getBoolean(BundleConstant.BUNDLE_CLEAR_STACK, false)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        }
        context.startActivity(intent);
    }

    /***
     * Open {@link Activity} using {@link Class} value, provides additional functionality for
     * passing {@link Bundle} data and clearing activity stack
     * @param context the calling context
     * @param className activity class name which needs to be opened.
     * @param passingBundle {@link Bundle} object which contains key value pairs which would
     * be passed to opening activity
     * @param clearActivityFromStack clear activity stack from system if passed {@code true}.
     *
     * @see  Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK
     */
    public static void genericIntent(Activity context, Class<?> className,
                                     Bundle passingBundle,
                                     boolean clearActivityFromStack) {
        Intent intent = new Intent();
        intent.setClass(context, className);
        if (passingBundle != null) {
            intent.putExtras(passingBundle);
            if (passingBundle.getBoolean(BundleConstant.BUNDLE_CLEAR_STACK, false)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        }
        if (clearActivityFromStack) {
            context.startActivity(intent);
            context.finish();
        }
    }


    /***
     * Open {@link Activity} using {@link Class} value with result, provides additional functionality for
     * passing {@link Bundle} data and clearing activity stack
     * @param activity {@link Activity} requesting result
     * @param className activity class name which needs to be opened.
     * @param passingBundle {@link Bundle} object which contains key value pairs which would
     * be passed to opening activity
     * @param requestCode request code that identifies your request.
     */
    public static void genericIntentForResult(Activity activity, Class<?> className,
                                              Bundle passingBundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, className);
        if (passingBundle != null) {
            intent.putExtras(passingBundle);
        }
        activity.startActivityForResult(intent, requestCode);

    }

    /***
     * Identifies user device Launcher name, and takes user to its main screen.
     * @param context the calling context
     */
    public static void gotoHomeScreen(Context context) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startMain);
    }

    /**
     * Display message on screen using {@link Toast}
     *
     * @param context  The calling context
     * @param message  message which would be displayed on screen
     * @param duration duration of the message
     * @see Toast
     */
    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();

    }

    /***
     * Opens phone dial screen with pre fill number
     * @param mContext the calling context
     * @param numberToCall number which needs to be called.
     */
    public static void callNumber(Context mContext, String numberToCall) {
        if (numberToCall == null) {
            return;
        }
        String url = "tel:" + numberToCall;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }

    }

    /**
     * Used to display message on Screen
     *
     * @param context    The calling context.
     * @param resourceId The resourceId of message we want to display.
     * @param duration   duration of the message
     * @see Toast
     */
    public static void showToast(Context context, @StringRes int resourceId, int duration) {
        Toast.makeText(context, resourceId, duration).show();

    }

    /***
     * Generates {@link AlertDialog} for showing no GPS dialog to user.
     * @param mContext the calling context
     */
    public static void buildAlertMessageNoGps(final Context mContext) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,
                                        @SuppressWarnings("unused") final int id) {
                        mContext.startActivity(
                                new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog,
                                @SuppressWarnings("unused") final int id) {
                dialog.cancel();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    /***
     * Opens Email client app on device for user with pre filled email address
     * @param mContext the calling context.
     * @param mEmail the email which needs to be pre filled within email client.
     */
    public static void openEmailTo(Context mContext, String mEmail) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        //Fill it with Data
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        if (emailIntent.resolveActivity(mContext.getPackageManager()) != null) {
            //Send it off to the Activity-Chooser
            mContext.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
    }

    /***
     * Opens Email client app on device for user with pre filled email address and various options
     * i.e. email subject, email body, recipient email address
     * @param mContext the calling context
     * @param subject subject which needs to be entered.
     * @param emailTo email of the recipient
     * @param emailBody email body
     */
    public static void sendEmail(Context mContext, String subject, String emailTo,
                                 String emailBody) {

        Intent emailIntent;
        emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setType("text/plain");
        emailIntent.setData(Uri.parse("mailto:" + emailTo));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
        if (emailIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }

    }

    /**
     * Check if location services enabled or not
     *
     * @param mContext the calling context.
     * @return return {@link true} if location services are enabled else returns false.
     */
    public static boolean areLocationServicesEnabled(Context mContext) {
        LocationManager lm = null;
        boolean gpsEnabled = false;
        boolean networkEnabled = false;
        if (lm == null) {
            lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return !(!gpsEnabled && !networkEnabled);
    }


    /***
     * Checks for External Storage permission.
     * @param mContext the calling context
     * @return return {@code true} if external storage permission is granted by user,
     * else return false
     *
     */
    public static boolean checkPermissionToAccessUserExternalStorage(Context mContext) {

        int hasLocationAccessPermission = ContextCompat.
                checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        return hasLocationAccessPermission == PackageManager.PERMISSION_GRANTED;
    }

    /***
     * Hide keyboard from screen forcefully
     * @param context the calling context
     * @param view Parent view in which keyboard is been shown on
     */
    public static void hideSoftKeyboard(Context context, View view) {
        if (view == null) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            return;
        }
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /***
     * Show Keyboard on screen forcefully
     * @param context the calling context
     * @param editText {@link EditText} view in which keyboard should be shown.
     */
    public static void showSoftKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    /***
     * Create a Drawable image for {@link View} with Alpha Value which can be used in any widgets.
     *
     * @param mContext          Current context
     * @param imageResourceFile Image resource File ID
     * @param alphaValue        value of alpha
     * @return return {@link Drawable} Object having alpha value
     */
    public static Drawable getAplhaDrawableImage(Context mContext, int imageResourceFile,
                                                 int alphaValue) {
        Drawable img = ContextCompat.getDrawable(mContext, imageResourceFile);
        Rect rect = new Rect(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
        img.setBounds(rect);
        img.setAlpha(alphaValue);
        return img;
    }

    /***
     *  Create a Drawable image for {@link View} which can be used in any widgets.
     * @param mContext calling context
     * @param imageResourceFile Image resource File ID
     * @return return {@link Drawable} Object
     */
    public static Drawable getDrawableImage(Context mContext, int imageResourceFile) {
        Drawable img = ContextCompat.getDrawable(mContext, imageResourceFile);
        Rect rect = new Rect(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
        img.setBounds(rect);
        return img;
    }


    /**
     * Checks if input email is valid or not
     *
     * @param target Email value which needs to be validated
     * @return {@code true} if user email is valid, else return false
     * @see Patterns#EMAIL_ADDRESS
     */
    public static boolean isValidEmail(CharSequence target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /***
     * Opens Google map screen with pre filled latitude and longitude.
     * @param context Calling context
     * @param latitude location latitude
     * @param longitude location longitude
     */
    public static void openDirectionIntent(Context context, double latitude, double longitude) {

        String uriForDirection = String.format(Locale.ENGLISH,
                "http://maps.google.com/maps?daddr=%f,%f", latitude, longitude);

        //String uriForDirection = "geo:" + latitude + "," + longitude + "?z=17";
        Uri gmmIntentUri = Uri.parse(uriForDirection);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }

    }


    // endregion

    //region Methods for serialize and deserialize Bundle

    /***
     *  Serialize {@link Bundle} object in base64 string which can be used later on.
     * @param bundle Bundle which needs to be serialized
     * @return return base64 string
     */
    public static String serializeBundle(final Bundle bundle) {
        String base64 = null;
        final Parcel parcel = Parcel.obtain();
        try {
            parcel.writeBundle(bundle);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final GZIPOutputStream zos = new GZIPOutputStream(new BufferedOutputStream(bos));
            zos.write(parcel.marshall());
            zos.close();
            base64 = Base64.encodeToString(bos.toByteArray(), 0);
        } catch (IOException e) {
            e.printStackTrace();
            base64 = null;
        } finally {
            parcel.recycle();
        }
        return base64;
    }

    /***
     * De-serialize string into {@link Bundle} object
     * @param base64 base64 string which needs to transform into {@link Bundle}
     * @return returns {@link Bundle} object
     */
    public static Bundle deserializeBundle(final String base64) {
        Bundle bundle = null;
        final Parcel parcel = Parcel.obtain();
        try {
            final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            final GZIPInputStream zis = new
                    GZIPInputStream(new ByteArrayInputStream(Base64.decode(base64, 0)));
            int len = 0;
            while ((len = zis.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
            zis.close();
            parcel.unmarshall(byteBuffer.toByteArray(), 0, byteBuffer.size());
            parcel.setDataPosition(0);
            bundle = parcel.readBundle();
        } catch (IOException e) {
            e.printStackTrace();
            bundle = null;
        } finally {
            parcel.recycle();
        }
        return bundle;
    }

    //endregion

    //region Helper Method for ChangeIcon Color

    /***
     * Programmatically tints drawable with provided color.
     * @param drawable Drawable which needs to be tinted.
     * @param context calling context
     * @param item {@link MenuItem} which needs to be tinted.
     * @param color Color resource id
     * @param forImages perform tint for image
     * @return return {@link Drawable} which provided tint color
     */
    public static Drawable tintIconColor(Drawable drawable, Context context, MenuItem item,
                                         @ColorRes int color, boolean forImages) {
        Drawable wrappedDrawable;
        if (forImages) {
            wrappedDrawable = DrawableCompat.wrap(drawable);
            Rect rect = new Rect(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            wrappedDrawable.setBounds(rect);
            DrawableCompat.setTintList(wrappedDrawable.mutate(),
                    ColorStateList.valueOf(ContextCompat.getColor(context, color)));
            return wrappedDrawable;
        } else {
            drawable = item.getIcon();
            Drawable wrapDrawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context, color));
            return wrapDrawable;
        }
    }
    //endregion

    //region Helper method for PreLollipop TextView & Buttons Vector Images

    /***
     * Programmatically set vector drawables icons on Pre lollipop devices.
     * @param resourceId resource id of vector drawable.
     * @param context calling context
     * @return returns {@link Drawable} object which contains vector drawable icon for prelollip
     * devices.
     */
    public static Drawable setVectorForPreLollipop(int resourceId, Context context) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(context.getResources(),
                    resourceId, context.getTheme());
        } else {
            icon = context.getResources().getDrawable(resourceId, context.getTheme());
        }

        return icon;
    }


    /***
     * Programmatically set vector drawables icons on {@link View}.
     * Sets the Drawables (if any) to appear to the left of, above, to the
     * right of, and below the text. Use {@link ApplicationConstant#DRAWABLE_NONE}
     * if you do not want a Drawable there. The Drawables' bounds will be set to their intrinsic
     * bounds.
     * @param textView {@link TextView} on which Drawable needs to be set.
     * @param resourceId Vector icon resource id
     * @param context calling context
     * @param position Position where Drawable needs to be set i.e. (Left,Right,Bottom,Up)
     * {@link ApplicationConstant#DRAWABLE_TOP} {@link ApplicationConstant#DRAWABLE_BOTTOM}
     * {@link ApplicationConstant#DRAWABLE_RIGHT} {@link ApplicationConstant#DRAWABLE_LEFT}
     * {@link ApplicationConstant#DRAWABLE_NONE}
     */
    public static void setVectorForPreLollipop(TextView textView, int resourceId, Context context,
                                               int position) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(context.getResources(), resourceId,
                    context.getTheme());
        } else {
            icon = context.getResources().getDrawable(resourceId, context.getTheme());
        }
        switch (position) {
            case ApplicationConstant.DRAWABLE_LEFT:
                textView.setCompoundDrawablesWithIntrinsicBounds(icon, null, null,
                        null);
                break;

            case ApplicationConstant.DRAWABLE_RIGHT:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon,
                        null);
                break;

            case ApplicationConstant.DRAWABLE_TOP:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, icon, null,
                        null);
                break;

            case ApplicationConstant.DRAWABLE_BOTTOM:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                        icon);
                break;
            default:
                break;
        }
    }

    //endregion

    //region Helper methods for Opening Shop screen for checkout

    /***
     * Clear whole application stack and opens Shop screen.
     * @param activity the calling {@link Activity} context
     */
    public static void clearStackAndOpenShopNow(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, MainActivity.class);
        intent.setFlags(NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
        //CartManager.getInstance().emptyCart();
    }
    //endregion
}
