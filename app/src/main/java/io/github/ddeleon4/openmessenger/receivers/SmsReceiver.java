package io.github.ddeleon4.openmessenger.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;

import java.util.Locale;

import de.greenrobot.event.EventBus;
import io.github.ddeleon4.openmessenger.utilities.LogUtil;

/**
 * Broadcast Receiver which listens for any new SMS messages received.
 * If a chat windows is open with the current contact, update the chat, else show a notification.
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SMS_Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.logIntent(TAG, intent);

        Bundle bundle = intent.getExtras();

        if (bundle != null){
            //---retrieve the SMS message received---
            try{
                SmsMessage[] msgs;
                String format = bundle.getString("format");
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for(int i=0; i < msgs.length; i++){

                    //building the message, SDK 23 forward requires the message format as well
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    else
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);


                    //getting the contents of the message
                    String msg_from = msgs[i].getOriginatingAddress();
                    String msgBody = msgs[i].getMessageBody();
                    int status = msgs[i].getStatus();
                    String serviceCenter = msgs[i].getServiceCenterAddress();
                    String subject = msgs[i].getPseudoSubject();


                    //getting other information
                    Locale locale = Locale.getDefault();
                    String iso = locale.getISO3Country();
                    String formattedNumber = PhoneNumberUtils.formatNumber(msg_from, iso);
//                    int threadId = ConversationsManager.getThreadIdWith(context, formattedNumber);
//                    long contactId = ContactsManager.getContactId(context, msg_from);
//
//
                    //Updating related databases. If the chat is visible the message is marked as read, unread otherwise
//                    int messageId = MmsSmsUtil.addSmsToInbox(
//                            context,
//                            threadId,
//                            msg_from,
//                            msgBody,
//                            status,
//                            serviceCenter,
//                            subject,
//                            contactId,
//                            MyApplication.isChatVisible(threadId)
//                    );
//                    ConversationsManager.updateEntry(context, threadId);


                    //checking if a chat with this person is visible
//                    if(MyApplication.isChatVisible(threadId)){
//                        EventBus.getDefault().post(
//                                new EventMmsSms(
//                                        EventMmsSms.EVENT_TYPE.RECEIVED,
//                                        SEND_TYPE.SMS,
//                                        messageId
//                                )
//                        );
//                        EventBus.getDefault().post(
//                                new GeneralBusEvents.EventMessageLog()
//                        );
//                    }
                    //if no chat with this person is visible, generate a notification
//                    else{
                        //showing a notification
//                        MmsSmsUtil.showMessageNotification(context, threadId, msg_from, msgBody);
//                        EventBus.getDefault().post(new GeneralBusEvents.EventMessageLog());
//                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
