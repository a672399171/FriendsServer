package com.zzuzl.service;

import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
public interface ActivityService {
    Result<Activity> searchActivities(int page,int pageSize);

    int getActivityCount();

    Result addActivity(Activity activity);

    Result deleteActivity(long id);
}
