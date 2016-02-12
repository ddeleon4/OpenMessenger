package io.github.ddeleon4.openmessenger.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Manages all the settings
 */
public class SettingsManager {
    private final static String PREF_NAME = "setting_preferences";

    private final static String AUTO_RETRIEVE_MMS = "auto_retrieve_mms";

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context){
        return getPreferences(context).edit();
    }

    public static boolean getAutoRetrieveMms(Context context){
        return getPreferences(context).getBoolean(AUTO_RETRIEVE_MMS, true);
    }

    public static void setAutoRetrieveMms(Context context, boolean state){
        getEditor(context).putBoolean(AUTO_RETRIEVE_MMS, state).apply();
    }

    /*TODO outgoing message sounds*/
    /*TODO notifications*/
    /*TODO notification sound/ringtone*/
    /*TODO vibrate*/
    /*TODO advanced*/
        /*TODO group mms vs multiple sms*/
        /*TODO roaming auto retrieve*/
        /*TODO sms delivery report*/
        /*TODO simple characters only*/
        /*TODO wireless alerts/broadcasts*/
            /*TODO show extreme threats*/
            /*TODO show severe threats*/
            /*TODO show AMBER alerts*/
            /*TODO turn on notifications*/
            /*TODO alert sounds duration*/
            /*TODO alert reminder*/
            /*TODO vibrate*/
            /*TODO speak alert message*/
            /*TODO show ETWS test broadcasts*/
            /*TODO show CMAS test broadcasts*/
            /*TODO show opt-out-dialog*/
}
