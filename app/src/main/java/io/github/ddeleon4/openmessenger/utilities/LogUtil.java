package io.github.ddeleon4.openmessenger.utilities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Performs general logging functions
 */
public class LogUtil {
    public static void logIntent(String tag, Intent intent){
        if(intent == null)
            return;

        Log.d(tag, "intent = " + intent.toString());
        Log.d(tag, "intent action = " + intent.getAction());
        
        logBundle(tag, intent.getExtras());
    }

    public static void logBundle(String tag, Bundle bundle){
        if(bundle == null){
            Log.d(tag, "bundle is null");
            return;
        }

        for(String key : bundle.keySet())
            Log.d(tag, "Bundle: " + key + " = " + bundle.get(key));
    }
}
