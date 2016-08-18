package com.zzuzl.controller;

import com.zzuzl.common.Constants;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Controller
@RequestMapping("activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @RequestMapping("/")
    @ResponseBody
    public Result<Activity> searchActivities() {
        // HttpSession session =
        return activityService.searchActivities(1, Constants.SMALL_COUNT);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addActivity(@ModelAttribute("activity") Activity activity) {
        // Result result = new Result();
        Result result = activityService.addActivity(activity);
        return result;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteActivity(@RequestParam(required = false,defaultValue = "0") Long id) {
        return activityService.deleteActivity(id);
    }
}
