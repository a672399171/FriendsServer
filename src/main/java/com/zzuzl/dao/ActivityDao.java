package com.zzuzl.dao;

import com.zzuzl.model.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDao {
    List<Activity> searchActivities(@Param("start") int start,
                                   @Param("count") int count);

    int getActivityCount();

    int addActivity(Activity activity);

    int deleteActivity(long id);
}
