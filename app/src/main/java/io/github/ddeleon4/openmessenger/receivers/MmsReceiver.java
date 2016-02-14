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

package io.github.ddeleon4.openmessenger.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import com.android.messaging.mmslib.pdu.AcknowledgeInd;
import com.android.messaging.mmslib.pdu.DeliveryInd;
import com.android.messaging.mmslib.pdu.EncodedStringValue;
import com.android.messaging.mmslib.pdu.GenericPdu;
import com.android.messaging.mmslib.pdu.NotificationInd;
import com.android.messaging.mmslib.pdu.NotifyRespInd;
import com.android.messaging.mmslib.pdu.PduHeaders;
import com.android.messaging.mmslib.pdu.PduParser;
import com.android.messaging.mmslib.pdu.ReadOrigInd;
import com.android.messaging.mmslib.pdu.ReadRecInd;
import com.android.messaging.mmslib.pdu.RetrieveConf;
import com.android.messaging.mmslib.pdu.SendConf;

import java.util.Locale;

import de.greenrobot.event.EventBus;
import io.github.ddeleon4.openmessenger.OMApplication;
import io.github.ddeleon4.openmessenger.userinterface.ChatActivity;
import io.github.ddeleon4.openmessenger.utilities.LogUtil;
import io.github.ddeleon4.openmessenger.utilities.SettingsManager;

/**
 * Broadcast Receiver which listens for any new MMS messages received.
 * If a chat windows is open with the current contact, update the chat, else show a notification.
 */
public class MmsReceiver extends BroadcastReceiver {
    private static final String TAG = "MMS_receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.logIntent(TAG, intent);

        final byte[] data = intent.getByteArrayExtra("data");
//        final PduParser parser = new PduParser(data, CarrierConfig.shouldSupportMmsContentDisposition());
        final PduParser parser = new PduParser(data, false);
        GenericPdu pdu = null;

        try {
            pdu = parser.parse();
        } catch (final RuntimeException e) {
            Log.e(TAG, "Invalid MMS WAP push", e);
        }

        if(pdu == null){
            Log.e(TAG, "Invalid WAP push data");
            return;
        }

        switch (pdu.getMessageType()) {
            case PduHeaders.MESSAGE_TYPE_ACKNOWLEDGE_IND: {
                Log.d(TAG, "acknowledgs Ind");
                AcknowledgeInd aInd = (AcknowledgeInd) pdu;
                /*TODO figure out this and update everything as needed*/
                break;
            }
            case PduHeaders.MESSAGE_TYPE_DELIVERY_IND: {
                Log.d(TAG, "delivery ind");
                DeliveryInd dInd = (DeliveryInd) pdu;
                /*TODO figure out this and update everything as needed*/
                break;
            }
            case PduHeaders.MESSAGE_TYPE_NOTIFICATION_IND: {
                Log.d(TAG, "notification ind");
                processNotificationInd(context, (NotificationInd) pdu);
                break;
            }
            case PduHeaders.MESSAGE_TYPE_NOTIFYRESP_IND: {
                Log.d(TAG, "notify resp ind");
                NotifyRespInd nRespInd = (NotifyRespInd) pdu;
                /*TODO figure out this and update everything as needed*/
                break;
            }
            case PduHeaders.MESSAGE_TYPE_READ_ORIG_IND: {
                Log.d(TAG, "read orig ind");
                ReadOrigInd rInd = (ReadOrigInd) pdu;
                /*TODO figure out this and update everything as needed*/
                break;
            }
            case PduHeaders.MESSAGE_TYPE_READ_REC_IND: {
                Log.d(TAG, "read rec ind");
                ReadRecInd rRecInd = (ReadRecInd) pdu;
                /*TODO figure out this and update everything as needed*/
                break;
            }
            case PduHeaders.MESSAGE_TYPE_RETRIEVE_CONF: {
                Log.d(TAG, "retrieve conf");
                RetrieveConf retrieveConf = (RetrieveConf) pdu;
                /*TODO figure out this and update everything as needed*/
            }
            case PduHeaders.MESSAGE_TYPE_SEND_CONF: {
                Log.d(TAG, "send conf");
                SendConf sConf = (SendConf) pdu;
                /*TODO figure out this and update everything as needed*/
                break;
            }
            default:{
                Log.d(TAG, "message type = " + pdu.getMessageType());
            }
        }
    }

    private void processNotificationInd(Context context, NotificationInd nInd){
        //correcting content location if needed
//        if (CarrierConfig.isTransactionIdEnabled()) {
//            byte[] contentLocation = nInd.getContentLocation();
//            Log.d(TAG, "content location " + new String(contentLocation));
//
//            if ('=' == contentLocation[contentLocation.length - 1]) {
//                byte[] transactionId = nInd.getTransactionId();
//                byte[] contentLocationWithId = new byte[contentLocation.length + transactionId.length];
//                System.arraycopy(contentLocation, 0, contentLocationWithId, 0, contentLocation.length);
//                System.arraycopy(transactionId, 0, contentLocationWithId, contentLocation.length, transactionId.length);
//                nInd.setContentLocation(contentLocationWithId);
//                Log.d(TAG, "correcting location to " + new String(contentLocationWithId));
//            }
//        }
//
//        //getting the number of the sender
//        EncodedStringValue encodedNumbers = nInd.getFrom();
//        String iso = Locale.getDefault().getISO3Country();
//        String senderNumber = PhoneNumberUtils.formatNumber(encodedNumbers.getString(), iso);
//
//        //updating related databases
//        int threadId = ConversationsManager.getThreadIdWith(context, senderNumber);
//        int messageId = MmsSmsUtil.storeMmsNotification(context, threadId, nInd);
//        ConversationsManager.updateEntry(context, threadId);
//
//        //download the message if allowed to
//        if (SettingsManager.getAutoRetrieveMms(context)) {
//            Intent downloadIntent = new Intent(context, MmsSmsService.class);
//            downloadIntent.putExtra(MmsSmsService.EXTRA_COMMAND, MmsSmsService.COMMAND_DOWNLOAD_MMS);
//            downloadIntent.putExtra(MmsSmsService.EXTRA_CONTENT_LOCATION, new String(nInd.getContentLocation()));
//            downloadIntent.putExtra(MmsSmsService.EXTRA_THREAD_ID, threadId);
//            downloadIntent.putExtra(MmsSmsService.EXTRA_MESSAGE_ID, messageId);
//            context.startService(downloadIntent);
//        }
//        //otherwise if the chat with this person is visible update it
//        else if(ChatActivity.getThreadId() == threadId){
//            EventBus.getDefault().post(
//                    new EventMmsSms(
//                            EventMmsSms.EVENT_TYPE.RECEIVED,
//                            SEND_TYPE.MMS,
//                            messageId
//                    )
//            );
//        }
//        //otherwise show notification
//        else{
//            MmsSmsUtil.showMessageNotification(
//                    context,
//                    threadId,
//                    senderNumber,
//                    "New MMS Message"
//            );
//        }
//
//        EventBus.getDefault().post(new GeneralBusEvents.EventMessageLog());
    }
}
