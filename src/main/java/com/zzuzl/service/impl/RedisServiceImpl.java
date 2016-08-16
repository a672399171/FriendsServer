package com.zzuzl.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzuzl.common.Common;
import com.zzuzl.dao.CommentDao;
import com.zzuzl.dao.LikeDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.model.Comment;
import com.zzuzl.model.Like;
import com.zzuzl.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<Like> syncActivityLike(long activityId) {
        List<Like> likes = likeDao.searchLikes(null, activityId, 0, 0);
        redisTemplate.boundHashOps(Common.HASH_LIKE).delete(activityId);
        redisTemplate.boundHashOps(Common.HASH_LIKE).put(activityId, likes);
        return likes;
    }

    public List<Comment> syncActivityComment(long activityId) {
        Result<Comment> commentResult = commentDao.searchComments(null, null, activityId, 0, 0);
        List<Comment> comments = commentResult.getList();
        redisTemplate.boundHashOps(Common.HASH_COMMENT).delete(activityId);
        redisTemplate.boundHashOps(Common.HASH_COMMENT).put(activityId, comments);
        return comments;
    }

    public List<Like> getActivityLikes(long activityId) {
        List<Like> likes = (List<Like>) redisTemplate.boundHashOps(Common.HASH_LIKE).get(activityId);
        if (likes == null) {
            likes = syncActivityLike(activityId);
        }
        return likes;
    }

    public List<Comment> getActivityComments(long activityId) {
        List<Comment> comments = (List<Comment>) redisTemplate.boundHashOps(Common.HASH_COMMENT).get(activityId);
        if (comments == null) {
            comments = syncActivityComment(activityId);
        }
        return comments;
    }

    public synchronized void syncLikeAndCommentToMysql() {
        Map<Object,Object> map = (Map<Object,Object>) redisTemplate.boundHashOps(Common.HASH_LIKE).entries();

        if(map != null) {
            for(Map.Entry<Object,Object> entry : map.entrySet()) {
                long activityId = (Long) entry.getKey();
                List<Like> likes = (List<Like>) entry.getValue();

            }
        }
        redisTemplate.delete(Common.HASH_LIKE);
    }

    public Result<Activity> searchActivities(int page, int count) {
        Result<Activity> result = new Result<Activity>();
        redisTemplate.boundHashOps(Common.HASH_ACTIVITY);


        return result;
    }
}
