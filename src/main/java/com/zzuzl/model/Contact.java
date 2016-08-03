package com.zzuzl.model;

import java.util.Date;

/**
 * Created by zhanglei53 on 2016/8/3.
 */
public class Contact {
    private User from;
    private User to;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
