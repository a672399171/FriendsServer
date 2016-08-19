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

    @RequestMapping("/listActivity")
    @ResponseBody
    public Result<Activity> searchActivities() {
        return activityService.searchActivities(1, Constants.SMALL_COUNT);
    }

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    @ResponseBody
    public Result addActivity(@ModelAttribute("activity") Activity activity) {
        return activityService.addActivity(activity);
    }

    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteActivity(@RequestParam(required = false, defaultValue = "0") Long id) {
        return activityService.deleteActivity(id);
    }

    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    @ResponseBody
    public Result addLike(Long activityId) {
        // TODO 增加学号
        String schoolNum = "20133410139";
        return activityService.addLike(schoolNum, activityId);
    }

    @RequestMapping(value = "/deleteLike", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteLike(Long activityId) {
        // TODO 增加学号
        String schoolNum = "20133410139";
        return activityService.deleteLike(schoolNum, activityId);
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(String to, Long activityId, String content) {
        // TODO 增加学号
        String from = "20133410139";
        return activityService.addComment(from, to, activityId, content);
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteComment(Long commentId) {
        return activityService.deleteComment(commentId);
    }
}
