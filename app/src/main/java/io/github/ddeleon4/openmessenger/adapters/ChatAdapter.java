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

package io.github.ddeleon4.openmessenger.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.ddeleon4.openmessenger.R;

/**
 * Adapter for the chat Activity
 */
public class ChatAdapter extends CursorAdapter {
    private static final String TAG = "ChatAdapter";

    private LayoutInflater inflater;

    public ChatAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int resource = 0;

        Holder holder = new Holder();
        holder.messageView = inflater.inflate(resource, parent, false);
        holder.contactPicture = (ImageView) holder.messageView.findViewById(R.id.contact_picture);
        holder.contactName = (TextView) holder.messageView.findViewById(R.id.contact_name);
        holder.messageTime = (TextView) holder.messageView.findViewById(R.id.message_time);
        holder.messageContainer = (LinearLayout) holder.messageView.findViewById(R.id.message_container);
        holder.messageView.setTag(holder);

        return holder.messageView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        /*TODO if view changed*/


    }

    private void setMessageSent(Holder holder){
        /*TODO change params of holder objects to be a sent message*/
    }

    private void setMessageReceived(Holder holder){
        /*TODO change params of holder objects to be a received message*/
    }

    private class Holder{
        View messageView = null;
        ImageView contactPicture = null;
        TextView contactName = null;
        TextView messageTime = null;
        LinearLayout messageContainer = null;
    }
}
