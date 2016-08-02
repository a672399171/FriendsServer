package com.zzuzl.service;

import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
public interface ActivityService {
    Result<Activity> searchActivitys(int page,int pageSize);

    int getActivityCount();
}
