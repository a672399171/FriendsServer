package com.zzuzl.service;

import com.zzuzl.model.Comment;
import com.zzuzl.model.Like;

import java.util.List;

/**
 * Created by zhanglei53 on 2016/8/15.
 */
public interface RedisService {

    List<Like> getActivityLikes(long activityId);

    List<Comment> getActivityComments(long activityId);

    List<Like> syncActivityLike(long activityId);

    List<Comment> syncActivityComment(long activityId);

    void syncLikeAndCommentToMysql();
}
