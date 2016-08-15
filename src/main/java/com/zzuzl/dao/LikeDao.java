package com.zzuzl.dao;

import com.zzuzl.model.Like;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhanglei53 on 2016/8/15.
 */
public interface LikeDao {

    int addLike(@Param("schoolNum") String schoolNum,
                @Param("activityId") long activityId);

    int deleteLike(@Param("schoolNum") String schoolNum,
                   @Param("activityId") long activityId);

    List<Like> searchLikes(@Param("schoolNum") String schoolNum,
                           @Param("activityId") long activityId,
                           @Param("start") int start,
                           @Param("count") int count);

    Like getLike(@Param("schoolNum") String schoolNum,
                 @Param("activityId") long activityId);
}
