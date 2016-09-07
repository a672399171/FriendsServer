package com.zzuzl.controller;

import com.zzuzl.common.Constants;
import com.zzuzl.common.annotaion.Authorization;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Activity;
import com.zzuzl.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @Authorization(Constants.AUTH_USER)
    @RequestMapping("/listActivity")
    @ResponseBody
    public Result<Activity> searchActivities(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return activityService.searchActivities(page, pageSize);
    }

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    @ResponseBody
    public Result addActivity(@ModelAttribute("activity") Activity activity) {
        return activityService.addActivity(activity);
    }

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteActivity(@RequestParam(required = false, defaultValue = "0") Long id) {
        return activityService.deleteActivity(id);
    }

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    @ResponseBody
    public Result addLike(Long activityId, HttpServletRequest request) {
        String schoolNum = (String) request.getAttribute(Constants.SCHOOLNUM);
        return activityService.addLike(schoolNum, activityId);
    }

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/deleteLike", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteLike(Long activityId, HttpServletRequest request) {
        String schoolNum = (String) request.getAttribute(Constants.SCHOOLNUM);
        return activityService.deleteLike(schoolNum, activityId);
    }

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(String to, Long activityId, String content, HttpServletRequest request) {
        String from = (String) request.getAttribute(Constants.SCHOOLNUM);
        return activityService.addComment(from, to, activityId, content);
    }

    @Authorization(Constants.AUTH_USER)
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteComment(Long commentId) {
        return activityService.deleteComment(commentId);
    }
}
