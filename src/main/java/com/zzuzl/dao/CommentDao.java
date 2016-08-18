package com.zzuzl.dao;

import com.zzuzl.model.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by zhanglei53 on 2016/8/15.
 */
public interface CommentDao {
    int addComment(@Param("from") String from,
                   @Param("to") String to,
                   @Param("activityId") long activityId,
                   @Param("content") String content);

    int deleteLike(@Param("commentId") long commentId);

    List<Comment> searchComments(@Param("from") String from,
                                 @Param("to") String to,
                                 @Param("activityId") long activityId,
                                 @Param("start") int start,
                                 @Param("count") int count);

    Comment getById(@Param("commentId") long commentId);
}
