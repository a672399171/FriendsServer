package com.zzuzl.controller;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanglei53 on 2016/8/4.
 */
@Controller
@RequestMapping("captcha")
public class CaptchaController {
    @Resource
    private KaptchaExtend kaptchaExtend;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void generate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        kaptchaExtend.captcha(req, resp);
    }
}
