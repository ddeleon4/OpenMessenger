package io.github.ddeleon4.openmessenger.models;

import android.net.Uri;
import android.telephony.PhoneNumberUtils;

import com.android.messaging.mmslib.util.ContentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Message data model
 */
public class Message {
    private int threadId = -1;
    private int messageId = -1;
    private long date = 0l;
    private String sender = null;
    private String textMessage = null;
    private List<MessageParts> partsList = new ArrayList<>();

    /**
     * This creates a message.
     * This should be used for SMS messages.
     * @param threadId       The thread to which this messages belongs to
     * @param messageId      This message's row id
     * @param date           The date for this message
     * @param sender         The phone number for who sent this message
     * @param textMessage    The text contained in this message, null if there is none
     * */
    public Message(int threadId, int messageId, long date, String sender, String textMessage){
        this.threadId = threadId;
        this.messageId = messageId;
        this.date = date;
        this.textMessage = textMessage;

        String isoCode = Locale.getDefault().getISO3Country();
        this.sender = PhoneNumberUtils.formatNumber(sender, isoCode);
    }

    /**
     * This creates a message.
     * This should be used for MMS messages.
     * @param threadId       The thread to which this messages belongs to
     * @param messageId      This message's row id
     * @param date           The date for this message
     * @param sender         The phone number for who sent this message
     * @param textMessage    The text contained in this message, null if there is none
     * @param parts          Any {@link MessageParts} which associated with this message
     * */
    public Message(int threadId, int messageId, long date, String sender, String textMessage, MessageParts... parts){
        this.threadId = threadId;
        this.messageId = messageId;
        this.date = date;
        this.textMessage = textMessage;

        String isoCode = Locale.getDefault().getISO3Country();
        this.sender = PhoneNumberUtils.formatNumber(sender, isoCode);

        partsList.addAll(Arrays.asList(parts));
    }

    /**
     * Adds {@link MessageParts} to the message.
     * @param parts    Any number of {@link MessageParts}
     * */
    public void addParts(MessageParts... parts){
        partsList.addAll(Arrays.asList(parts));
    }

    /**
     * Returns the thread id to which this message belongs.
     * @return The thread id
     * */
    public int getThreadId() {
        return threadId;
    }

    /**
     * Returns this message's row id from the SMS or MMS table.
     * @return The message's row id
     * */
    public int getMessageId() {
        return messageId;
    }

    /**
     * Returns the date for this message.
     * @return the message's date
     * */
    public long getDate() {
        return date;
    }

    /**
     * Returns the sender's phone number of the message
     * @return The sender's phone number
     * */
    public String getSender() {
        return sender;
    }

    /**
     * Returns the message text for this message
     * @return The message text
     * */
    public String getTextMessage() {
        return textMessage;
    }

    /**
     * Returns a List of {@link MessageParts} associated with this message.
     * @return A list of {@link MessageParts}
     * */
    public List<MessageParts> getPartsList() {
        return partsList;
    }
}
