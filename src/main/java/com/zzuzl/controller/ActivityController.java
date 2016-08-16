package com.zzuzl.controller;

import com.zzuzl.common.Common;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @RequestMapping("/")
    @ResponseBody
    public Result<Activity> searchActivities() {

        return activityService.searchActivities(1, Common.SMALL_COUNT);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addActivity(@Valid @ModelAttribute("activity") Activity activity, BindingResult bindingResult) {
        Result result = activityService.addActivity(activity);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteActivity(@RequestParam(required = false,defaultValue = "0") Long id) {
        return activityService.deleteActivity(id);
    }
}
