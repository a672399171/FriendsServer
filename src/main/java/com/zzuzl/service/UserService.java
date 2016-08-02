package com.zzuzl.service;

import com.zzuzl.dto.Result;
import com.zzuzl.model.User;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
public interface UserService {
    Result login(String schoolNum, String password);

    User searchBySchoolNum(String schoolNum);

    Result addUser(User user);
}
