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

package io.github.ddeleon4.openmessenger.models;

import com.android.messaging.mmslib.util.ContentType;

/**
 * Message parts data model.
 */
public class MessageParts {
    private int id = -1;
    private int order = 0;
    private String contentType = null;
    private String filename = null;

    public MessageParts(int id, int order, String contentType, String filename) {
        this.id = id;
        this.order = order;
        this.contentType = contentType;
        this.filename = filename;
    }

    public int getOrder(){
        return order;
    }

    public String getFilename(){
        return filename;
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
