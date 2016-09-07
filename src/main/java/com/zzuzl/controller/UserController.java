package com.zzuzl.controller;

import com.zzuzl.common.Constants;
import com.zzuzl.common.Firebase;
import com.zzuzl.common.annotaion.Authorization;
import com.zzuzl.dto.Result;
import com.zzuzl.model.User;
import com.zzuzl.service.UserService;
import com.zzuzl.util.AuthUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.zzuzl.common.Constants.JSON_TYPE;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private Firebase firebase;
    @Resource
    private AuthUtil authUtil;
    @Resource
    private UserService userService;
    private Logger logger = LogManager.getLogger(getClass());

    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String schoolNum, String password) {
        Result result = userService.login(schoolNum, password);
        if (result.isSuccess()) {
            try {
                User user = (User) result.getData().get("user");
                if (userService.searchBySchoolNum(user.getSchoolNum()) == null) {
                    userService.addUser(user);
                }

                // String token = firebase.getAuth().createCustomToken(user.getSchoolNum(), claims);
                String token = authUtil.genToken(user);
                result.getData().put("token", token);
                result.getData().put("user", user);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return result;
    }

    // 验证
    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public Result auth() {
        Result result = new Result();
        return result;
    }

}
