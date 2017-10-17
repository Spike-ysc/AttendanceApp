package com.example.yan.attendanceapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yan.attendanceapp.Adapter.MessageAdapter;
import com.example.yan.attendanceapp.data.MessageListData;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<MessageListData> list;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.message_Recycle_view);
        initData();
        MessageAdapter adapter = new MessageAdapter(getContext(), list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        return view;
    }
    private void initData(){
        list = new ArrayList<>();
        for (int i=0; i<10; i++){
            MessageListData item = new MessageListData();
            item.setName("Name");
            item.setMessage("你有一门课需要签到，十分钟后结束");
            item.setTime("10:08");
            item.setUserImg(R.drawable.head);
            list.add(item);
        }
    }

}
