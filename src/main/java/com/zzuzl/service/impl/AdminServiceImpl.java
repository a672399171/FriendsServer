package com.zzuzl.service.impl;

import com.zzuzl.common.util.Utils;
import com.zzuzl.dao.AdminDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Admin;
import com.zzuzl.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhanglei53 on 2016/8/4.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    public Result login(String username, String password) {
        Result result = new Result();

        Admin admin = adminDao.search(username);
        if (admin != null) {
            if (!Utils.MD5(password, admin.getSalt()).equals(admin.getPassword())) {
                result.setSuccess(false);
                result.setError("密码错误");
            } else {
                admin.setSalt(null);
                admin.setPassword(null);
                result.getData().put("admin", admin);
            }
        } else {
            result.setSuccess(false);
            result.setError("用户不存在");
        }
        return result;
    }
}
