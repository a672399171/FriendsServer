package com.zzuzl.worker;

import com.zzuzl.service.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("syncLikeAndComment")
public class SyncLikeAndComment implements DisposableBean {
    @Resource
    private RedisService redisService;

    private static final Logger logger = LogManager.getLogger(SyncLikeAndComment.class);

    public void execute() {
        logger.info("========= sync like and comment =========");
        redisService.syncLikeAndCommentToMysql();
    }

    public void destroy() throws Exception {
        logger.info("========= worker dispose =========");
        redisService.syncLikeAndCommentToMysql();
    }
}
