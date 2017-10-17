package com.example.yan.attendanceapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yan.attendanceapp.R;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by yan on 2017/10/15.
 */

public class GroupExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGroupList;
    private List<List<String>> mChildList;

    public GroupExpandableListAdapter(Context context, List<String>group,
                                      List<List<String>> child){
        mContext = context;
        this.mGroupList = group;
        this.mChildList = child;
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_list_group,parent,false);
            viewHolder = new GroupViewHolder();
            viewHolder.groupImg = (ImageView)convertView.findViewById(R.id.expandable_list_group_img);
            viewHolder.groupName = (TextView)convertView.findViewById(R.id.expandable_list_group_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupViewHolder)convertView.getTag();
        }
        //展开改变groups的图标
        if (isExpanded){
            viewHolder.groupImg.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        }else {
            viewHolder.groupImg.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
        }
        viewHolder.groupName.setText(mGroupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_list_child,parent,false);
            viewHolder = new ChildViewHolder();
            viewHolder.childName = (TextView)convertView.findViewById(R.id.expandable_list_child_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildViewHolder)convertView.getTag();
        }
        viewHolder.childName.setText(mChildList.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private class GroupViewHolder{
        private ImageView groupImg;
        private TextView groupName;
    }
    private class ChildViewHolder{
        private TextView childName;
    }
}
