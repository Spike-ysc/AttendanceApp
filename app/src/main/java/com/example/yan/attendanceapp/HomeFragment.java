package com.example.yan.attendanceapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.yan.attendanceapp.Adapter.GroupExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ExpandableListView mListView;
    private GroupExpandableListAdapter mAdapter;
    private List<String> groups;
    private List<List<String>> childs;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        mListView = (ExpandableListView) view.findViewById(R.id.group_list);
        mAdapter = new GroupExpandableListAdapter(getContext(), groups, childs);
        mListView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    private void initData(){
        groups = new ArrayList<>();
        groups.add("我创建的群组");
        groups.add("我加入的群组");

        childs = new ArrayList<>();
        List<String> child1 = new ArrayList<>();
        child1.add("数据结构");
        child1.add("计算机组成原理");
        child1.add("计算机网络");

        List<String> child2 = new ArrayList<>();
        child2.add("C#程序设计");
        child2.add("Java面向对象");
        child2.add("专业英语");

        childs.add(child1);
        childs.add(child2);
    }

}
