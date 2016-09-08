package com.zzuzl.model;

import com.zzuzl.dto.Result;
import com.zzuzl.common.util.ObjectUtil;
import com.zzuzl.common.util.StringUtil;

import java.util.Date;

/**
 * Created by zhanglei53 on 2016/8/15.
 */
public class Comment extends BaseBean {
    private long commentId;
    private User from;
    private User to;
    private String content;
    private Activity activity;
    private Date createTime;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Result isValid() {
        Result result = new Result();
        if (ObjectUtil.hasNull(from, to, activity, content)) {
            result.setSuccess(false);
            result.setError("部分内容为空");
        } else if (!StringUtil.isSchoolNum(from.getSchoolNum()) ||
                !StringUtil.isSchoolNum(to.getSchoolNum()) ||
                StringUtil.isEmpty(content)) {
            result.setSuccess(false);
            result.setError("学号为空或内容为空");
        }
        return result;
    }
}
