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

    public Result addActivity(Activity activity) {
        Result result = new Result();
        if(activityDao.addActivity(activity) < 1) {
            result.setSuccess(false);
            result.setError("发表失败");
        }
        return result;
    }

    public Result deleteActivity(long id) {
        Result result = new Result();
        if(activityDao.deleteActivity(id) < 1) {
            result.setSuccess(false);
            result.setError("删除失败");
        }
        return result;
    }
}
