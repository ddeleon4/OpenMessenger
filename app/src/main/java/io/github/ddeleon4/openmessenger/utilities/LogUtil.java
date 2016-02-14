/*  Copyright 2016 Daniel Alejandro De Leon
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
* */

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
