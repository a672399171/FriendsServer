package com.zzuzl.controller;

import com.zzuzl.common.Common;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @RequestMapping("/")
    @ResponseBody
    public Result<Activity> searchActivitys() {
        return activityService.searchActivitys(1, Common.SMALL_COUNT);
    }
}
