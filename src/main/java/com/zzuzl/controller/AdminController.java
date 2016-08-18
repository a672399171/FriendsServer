package com.zzuzl.controller;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.zzuzl.common.Constants;
import com.zzuzl.common.annotaion.Authorization;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Admin;
import com.zzuzl.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhanglei53 on 2016/8/4.
 */
@Controller
@RequestMapping(Constants.ADMIN)
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private KaptchaExtend kaptchaExtend;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private Result login(String username, String password,
                         @RequestParam(required = false, defaultValue = "") String captcha,
                         HttpServletRequest request) {
        Result result = new Result();
        if (!captcha.equalsIgnoreCase(kaptchaExtend.getGeneratedKey(request))) {
            result.setSuccess(false);
            result.setError("验证码错误!");
        } else {
            result = adminService.login(username, password);
        }

        if(result.isSuccess()) {
            Admin admin = (Admin) result.getData().get(Constants.ADMIN);
            request.getSession().setAttribute(Constants.ADMIN,admin);
        }

        return result;
    }

    @Authorization(Constants.AUTH_ADMIN)
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    private Result current(HttpSession session) {
        Result result = new Result();
        result.getData().put(Constants.ADMIN,session.getAttribute(Constants.ADMIN));
        return result;
    }
}
