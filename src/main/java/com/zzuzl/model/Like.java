package com.zzuzl.model;

import java.util.Date;

/**
 * Created by zhanglei53 on 2016/8/15.
 */
public class Like {
    private User user;
    private Activity activity;
    private Date createTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
