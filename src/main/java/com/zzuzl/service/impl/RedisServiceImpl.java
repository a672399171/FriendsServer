package com.zzuzl.service.impl;

import com.zzuzl.common.Constants;
import com.zzuzl.dao.ActivityDao;
import com.zzuzl.dao.CommentDao;
import com.zzuzl.dao.LikeDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.model.Comment;
import com.zzuzl.model.Like;
import com.zzuzl.service.ActivityService;
import com.zzuzl.service.RedisService;
import com.zzuzl.util.ObjectUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private LikeDao likeDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private ActivityDao activityDao;
    @Resource
    private ActivityService activityService;

    public List<Like> syncActivityLike(long activityId) {
        List<Like> likes = likeDao.searchLikes(null, activityId, 0, 0);
        redisTemplate.boundHashOps(Constants.HASH_LIKE).delete(activityId);
        redisTemplate.boundHashOps(Constants.HASH_LIKE).put(activityId, likes);
        return likes;
    }

    public List<Comment> syncActivityComment(long activityId) {
        List<Comment> comments = commentDao.searchComments(null, null, activityId, 0, 0);
        redisTemplate.boundHashOps(Constants.HASH_COMMENT).delete(activityId);
        redisTemplate.boundHashOps(Constants.HASH_COMMENT).put(activityId, comments);
        return comments;
    }

    public List<Like> getActivityLikes(long activityId) {
        List<Like> likes = (List<Like>) redisTemplate.boundHashOps(Constants.HASH_LIKE).get(activityId);
        if (likes == null) {
            likes = syncActivityLike(activityId);
        }
        return likes;
    }

    public List<Comment> getActivityComments(long activityId) {
        List<Comment> comments = (List<Comment>) redisTemplate.boundHashOps(Constants.HASH_COMMENT).get(activityId);
        if (comments == null) {
            comments = syncActivityComment(activityId);
        }
        return comments;
    }

    public Result<Activity> searchActivities(int page, int count) {
        Result<Activity> result = new Result<Activity>(page, count);
        List<Activity> activities = new ArrayList<Activity>();
        List<Object> objects = redisTemplate.boundListOps(Constants.HASH_ACTIVITY).range((page - 1) * count, page * count);
        if (objects == null) {
            result = activityService.searchActivities(page, count);

        } else {
            for (Object object : objects) {
                if (object instanceof Activity) {
                    activities.add((Activity) object);
                }
            }
            result.setList(activities);
        }

        return result;
    }

    public void clearActivityData() {
        redisTemplate.delete(Constants.HASH_ACTIVITY);
    }
}
