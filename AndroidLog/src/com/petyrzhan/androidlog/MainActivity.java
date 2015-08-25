
package com.petyrzhan.androidlog;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
    
    private LogListView mLogListView;
    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mLogListView = (LogListView)findViewById(R.id.listview_log);
        mHandler = new MyHandler();
        
        findViewById(R.id.add_log_btn).setOnClickListener(this);
        findViewById(R.id.lock_btn).setOnClickListener(this);
    }
    

    /** 
     * @param v 
     * @see android.view.View.OnClickListener#onClick(android.view.View) 
     */ 	
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_log_btn:
                mHandler.sendEmptyMessage(MSG_LOG);
                break;
            case R.id.lock_btn:
                mLogListView.changeLock();
                break;

            default:
                break;
        }
    }
    
    private int count = 0;
    private final int MSG_LOG = 1126;
    private class MyHandler extends Handler {
	
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOG:
                    mLogListView.addLogText("hello world : " + count);
                    count++;
                    sendEmptyMessageDelayed(MSG_LOG, 300);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
