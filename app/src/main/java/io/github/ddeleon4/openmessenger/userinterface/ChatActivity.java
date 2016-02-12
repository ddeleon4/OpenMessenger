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
