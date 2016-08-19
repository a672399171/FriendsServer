package com.zzuzl.service.impl;

import com.zzuzl.dao.ActivityDao;
import com.zzuzl.dao.CommentDao;
import com.zzuzl.dao.LikeDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.model.Comment;
import com.zzuzl.model.Like;
import com.zzuzl.model.User;
import com.zzuzl.service.ActivityService;
import com.zzuzl.service.RedisService;
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
    @Resource
    private LikeDao likeDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private RedisService redisService;

    public Result<Activity> searchActivities(int page, int pageSize) {
        Result<Activity> result = new Result<Activity>(page, pageSize);
        List<Activity> activities = activityDao.searchActivities((page - 1) * pageSize, pageSize);

        for (Activity activity : activities) {
            activity.setLikes(likeDao.searchLikes(null, activity.getActivityId(), 0, 0));
            activity.setComments(commentDao.searchComments(null, null, activity.getActivityId(), 0, 0));
        }

        result.setTotalItem(activityDao.getActivityCount());
        result.setList(activities);
        return result;
    }

    public int getActivityCount() {
        return activityDao.getActivityCount();
    }

    public Result addActivity(Activity activity) {
        Result result = new Result();
        if (activityDao.addActivity(activity) < 1) {
            result.setSuccess(false);
            result.setError("发表失败");
        }

        return result;
    }

    public Result deleteActivity(long id) {
        Result result = new Result();
        if (activityDao.deleteActivity(id) < 1) {
            result.setSuccess(false);
            result.setError("删除失败");
        }
        return result;
    }

    public Result addLike(String schoolNum, Long activityId) {
        Result result = new Result();
        if (activityId == null) {
            result.setSuccess(false);
            result.setError("动态不存在");
        } else if (likeDao.addLike(schoolNum, activityId) < 1) {
            result.setSuccess(false);
            result.setError("添加失败");
        }
        return result;
    }

    public Result deleteLike(String schoolNum, Long activityId) {
        Result result = new Result();
        if (activityId == null) {
            result.setSuccess(false);
            result.setError("动态不存在");
        } else if (likeDao.deleteLike(schoolNum, activityId) < 1) {
            result.setSuccess(false);
            result.setError("删除失败");
        }
        return result;
    }

    public Result addComment(String from, String to, Long activityId, String content) {
        User f = new User();
        f.setSchoolNum(from);

        User t = new User();
        t.setSchoolNum(to);

        Activity activity = new Activity();
        activity.setActivityId(activityId);

        Comment comment = new Comment();
        comment.setFrom(f);
        comment.setTo(t);
        comment.setActivity(activity);
        comment.setContent(content);

        Result result = comment.isValid();
        if (result.isSuccess()) {
            if (commentDao.addComment(from, to, activityId, content) < 1) {
                result.setSuccess(false);
                result.setError("添加失败");
            }
        }

        return result;
    }

    public Result deleteComment(Long commentId) {
        Result result = new Result();
        if(commentDao.deleteComment(commentId) < 1) {
            result.setSuccess(false);
            result.setError("删除失败");
        }
        return result;
    }
}
