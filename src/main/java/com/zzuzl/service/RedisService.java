package com.zzuzl.service;

import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.model.Comment;
import com.zzuzl.model.Like;

import java.util.List;

public interface RedisService {

    List<Like> getActivityLikes(long activityId);

    List<Comment> getActivityComments(long activityId);

    List<Like> syncActivityLike(long activityId);

    List<Comment> syncActivityComment(long activityId);

    Result<Activity> searchActivities(int page,int count);

    void clearActivityData();
}
