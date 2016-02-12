package io.github.ddeleon4.openmessenger.utilities;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.android.messaging.mmslib.pdu.SendReq;

public class SendService extends Service {
    public SendService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void sendSms(Context context, String to, String message){

    }

    public void sendMms(Context context, SendReq sendReq){

    }
}
