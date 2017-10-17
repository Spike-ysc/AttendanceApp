package com.example.yan.attendanceapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yan.attendanceapp.R;
import com.example.yan.attendanceapp.data.MessageListData;

import java.util.List;

/**
 * Created by yan on 2017/10/15.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MessageListData> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public MessageAdapter(Context context, List<MessageListData> list){
        this.mContext = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.message_list, null);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder hoder1 = (MyViewHoder)holder;
        MessageListData item = list.get(position);
        hoder1.mName.setText(item.getName());
        hoder1.mMessage.setText(item.getMessage());
        hoder1.mTime.setText(item.getTime());
        hoder1.mUserImg.setImageResource(item.getUserImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHoder extends RecyclerView.ViewHolder{
        private TextView mName, mMessage, mTime;
        private ImageView mUserImg;
        public MyViewHoder(View itemView){
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.user_name);
            mMessage = (TextView) itemView.findViewById(R.id.user_message);
            mTime = (TextView) itemView.findViewById(R.id.user_time);
            mUserImg = (ImageView) itemView.findViewById(R.id.message_topic_image);
        }
    }
}
