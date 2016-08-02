package com.zzuzl.service.impl;

import com.zzuzl.common.util.NetUtil;
import com.zzuzl.dao.UserDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.User;
import com.zzuzl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public Result login(String schoolNum, String password) {
        return NetUtil.connect("http://jw.zzu.edu.cn/scripts/stuinfo.dll/check",
                schoolNum,password,"input[name='userid']");
    }

    public User searchBySchoolNum(String schoolNum) {
        return userDao.searchBySchoolNum(schoolNum);
    }

    public Result addUser(User user) {
        Result result = new Result();
        if(searchBySchoolNum(user.getSchoolNum()) != null) {
            result.setSuccess(false);
            result.setError("用户已存在");
        } else if(userDao.addUser(user) < 1){
            result.setSuccess(false);
            result.setError("添加失败");
        }
        return result;
    }
}
