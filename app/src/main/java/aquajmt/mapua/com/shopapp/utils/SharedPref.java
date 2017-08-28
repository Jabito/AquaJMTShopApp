package aquajmt.mapua.com.shopapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import aquajmt.mapua.com.shopapp.api.models.OrderInfo;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.models.ShopLogin;

/**
 * Created by Jabito on 27/08/2017.
 */

public class SharedPref {

    public static ShopInfo shopInfo = new ShopInfo();
    public static ShopLogin shopLogin = new ShopLogin();
    public static OrderInfo currentOrder = new OrderInfo();

    public static String masterUSERNAME = "masterUSERNAME";
    public static String masterPASSWORD = "masterPASSWORD";

    public static String SHOP_ID = "SHOP_ID";
    public static String USER = "USER";

    public void setBoolValue(String Key, boolean value, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("LOG", value);
        editor.commit();
    }

    public static boolean getBoolValue(String Key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        return preferences.getBoolean("LOG", false);
    }

    public static void setStringValue(String Key, String specID, String value, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(specID, value);
        editor.apply();
    }

    public static   String getStringValue(String Key, String specID, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        return preferences.getString(specID, "null");
    }
}
