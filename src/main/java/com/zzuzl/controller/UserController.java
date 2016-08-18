package com.zzuzl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzuzl.common.Constants;
import com.zzuzl.common.Firebase;
import com.zzuzl.common.annotaion.Authorization;
import com.zzuzl.dto.Result;
import com.zzuzl.model.User;
import com.zzuzl.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private Firebase firebase;
    @Resource
    private UserService userService;
    private Logger logger = LogManager.getLogger(getClass());

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(String schoolNum, String password) {
        Result result = userService.login(schoolNum, password);
        if (result.isSuccess()) {
            User user = (User) result.getData().get("user");
            userService.addUser(user);

            Map<String,Object> claims = new HashMap<String, Object>();
            claims.put(Constants.USER,user);
            claims.put(Constants.EXP,System.currentTimeMillis());

            String token = firebase.getAuth().createCustomToken(user.getSchoolNum(),claims);
            result.getData().put("token", token);
            result.getData().put("user",user);
        }
        return result;
    }

}
