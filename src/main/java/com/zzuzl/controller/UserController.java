package com.zzuzl.controller;

import com.zzuzl.common.Firebase;
import com.zzuzl.dto.Result;
import com.zzuzl.model.User;
import com.zzuzl.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private Firebase firebase;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(String schoolNum, String password) {
        Result result = userService.login(schoolNum, password);
        if (result.isSuccess()) {
            User user = (User) result.getData().get("user");
            userService.addUser(user);
            String token = firebase.getAuth().createCustomToken(user.getSchoolNum());
            result.getData().put("token", token);
        }
        return result;
    }
}
