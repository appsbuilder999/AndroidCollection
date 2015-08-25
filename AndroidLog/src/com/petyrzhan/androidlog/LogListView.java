/**   
 * Description  : TODO
 * @Title       : LogListView.java 
 * @Package     : com.petyrzhan.androidlog 
 * @author 	    : petyr.zhan  
 * @date        : 2015年8月24日 下午5:39:26 
 * @version     : 1.0
 */


package com.petyrzhan.androidlog;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/** 
 * <h3>Description	: 日志显示类，不用textview便于扩展</h3>
 * @author      : petyr.zhan
 * @date        : 2015年8月24日 下午5:39:26 
 * @version     : V1.0
 */

public class LogListView extends ListView{

    
    private MyAdapter mAdapter;
    private ArrayList<String> mLogList;
    private boolean mLock = false;
    
    /**
     * Description : 构造函数
     * @param context
     * @param attrs
     */
    	
    public LogListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    	
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }
    	
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        uninit();
    }
    
    private void init() {
        mLogList = new ArrayList<String>();
        mAdapter = new MyAdapter();
        setAdapter(mAdapter);
        
    }
    
    private void uninit() {
        mLogList.clear();
        mAdapter = null;
        mLogList = null;
    }
    
    public void addLogText(String log) {
        mLogList.add(log);
        mAdapter.notifyDataSetChanged();
        if(mLock) {
            setSelection(getBottom()); 
        } 
    }
    
    public void changeLock() {
        mLock = !mLock;
    }
    
    private class MyAdapter extends BaseAdapter {
	
        @Override
        public int getCount() {
            return mLogList.size();
        }
 	
        @Override
        public Object getItem(int position) {
            return null;
        }
 	
        @Override
        public long getItemId(int position) {
            return 0;
        }
	
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = LayoutInflater.from(LogListView.this.getContext()).inflate(R.layout.log_item, null);
            }
            
            TextView tv = (TextView)convertView.findViewById(R.id.tv);
            tv.setText(mLogList.get(position));
            
            return convertView;
        }
    }
}
