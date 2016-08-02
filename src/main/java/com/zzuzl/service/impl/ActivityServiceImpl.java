package com.zzuzl.service.impl;

import com.zzuzl.dao.ActivityDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;

    public Result<Activity> searchActivitys(int page, int pageSize) {
        Result<Activity> result = new Result<Activity>(page, pageSize);
        List<Activity> activities = activityDao.searchActivitys((page - 1) * pageSize, pageSize);

        result.setTotalItem(activityDao.getActivityCount());
        result.setList(activities);
        return result;
    }

    public int getActivityCount() {
        return activityDao.getActivityCount();
    }
}
