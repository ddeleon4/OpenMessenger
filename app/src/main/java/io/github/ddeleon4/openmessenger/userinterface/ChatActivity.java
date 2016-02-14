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

package io.github.ddeleon4.openmessenger.userinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.ddeleon4.openmessenger.R;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";

    public static final String EXTRA_THREAD_ID = "thread_id";

    private static int threadId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //getting the chat thread id
        getIntent().getIntExtra(EXTRA_THREAD_ID, -1);
        //if the thread id is invalid, finish this chat
        if(threadId == -1)
            finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void attachClicked(View view){
        /*todo*/
    }

    public void sendClicked(View view){
        /*todo*/
    }

    public static int getThreadId(){
        return threadId;
    }
}
