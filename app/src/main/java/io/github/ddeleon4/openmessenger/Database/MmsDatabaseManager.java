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

package io.github.ddeleon4.openmessenger.database;

import android.content.Context;

import com.android.messaging.mmslib.pdu.NotificationInd;
import com.android.messaging.mmslib.pdu.RetrieveConf;
import com.android.messaging.mmslib.pdu.SendReq;

/**
 * Manages the data in the device's Telephony MMS tables
 */
public class MmsDatabaseManager {
    private static final String TAG = "MmsDatabaseManger";

    public static void storeMms(Context context, NotificationInd nInd){
        /*TODO*/
    }

    public static void storeMms(Context context, RetrieveConf rConf){
        /*TODO*/
    }

    public static void storeMms(Context context, SendReq sReq){
        /*TODO*/
    }
}
