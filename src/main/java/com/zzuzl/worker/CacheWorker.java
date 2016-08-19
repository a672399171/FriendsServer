package com.zzuzl.worker;

import com.zzuzl.service.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("cacheWorker")
public class CacheWorker {
    @Resource
    private RedisService redisService;

    private static final Logger logger = LogManager.getLogger(CacheWorker.class);

    public void execute() {
        logger.info("========= clear redis activity data =========");
        redisService.clearActivityData();
    }

}
