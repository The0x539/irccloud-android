package com.irccloud.android;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MessageActivity extends SherlockFragmentActivity {
	BuffersDataSource.Buffer buffer;
	TextView messageTxt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        messageTxt = (TextView)findViewById(R.id.messageTxt);
        Button sendBtn = (Button)findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkConnection conn = NetworkConnection.getInstance();
				if(conn.getState() == NetworkConnection.STATE_CONNECTED) {
					conn.say(buffer.cid, buffer.name, messageTxt.getText().toString());
					messageTxt.setText("");
				}
			}
        	
        });
    }

    @Override
    public void onResume() {
    	super.onResume();
    	buffer = BuffersDataSource.getInstance().getBuffer((int)getIntent().getLongExtra("bid", 0));
    	getSupportActionBar().setTitle(buffer.name);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
