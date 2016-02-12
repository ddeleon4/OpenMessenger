package io.github.ddeleon4.openmessenger.models;

import android.net.Uri;

import com.android.messaging.mmslib.util.ContentType;

/**
 * Message parts data model.
 */
public class MessageParts {
    private int order = 0;
    private Uri dataLocation = null;
    private String contentType = null;

    public MessageParts(int order, String dataLocationUri, String contentType) {
        this.order = order;
        this.dataLocation = Uri.parse(dataLocationUri);
        this.contentType = contentType;
    }

    public MessageParts(int order, Uri dataLocation, String contentType) {
        this.order = order;
        this.dataLocation = dataLocation;
        this.contentType = contentType;
    }

    public int getOrder(){
        return order;
    }

    public Uri getDataLocation(){
        return dataLocation;
    }

    public String getContentType(){
        return contentType;
    }

    public boolean isImage(){
        return ContentType.isImageType(contentType);
    }

    public boolean isVideo(){
        return ContentType.isVideoType(contentType);
    }

    public boolean isAudio(){
        return ContentType.isAudioType(contentType);
    }

    public boolean isContactVCard(){
        return ContentType.isVCardType(contentType);
    }
}
