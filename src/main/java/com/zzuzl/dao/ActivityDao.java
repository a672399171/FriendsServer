package com.zzuzl.dao;

import com.zzuzl.model.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
public interface ActivityDao {
    List<Activity> searchActivitys(@Param("start") int start,
                                   @Param("count") int count);

    int getActivityCount();

    int addActivity(Activity activity);

    int deleteActivity(long id);
}
