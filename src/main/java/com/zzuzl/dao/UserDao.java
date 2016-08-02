package com.zzuzl.dao;

import com.zzuzl.model.User;

/**
 * Created by zhanglei53 on 2016/8/1.
 */
public interface UserDao {
    User searchBySchoolNum(String schoolNum);

    int addUser(User user);
}
