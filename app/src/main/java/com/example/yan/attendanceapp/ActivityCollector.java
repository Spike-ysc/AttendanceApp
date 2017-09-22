package com.example.yan.attendanceapp;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 2017/9/22.
 */

public class ActivityCollector {
    public static List<Activity> sActivities = new ArrayList<>();

    public static void addActivity(Activity activity){
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity){
        sActivities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:sActivities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
