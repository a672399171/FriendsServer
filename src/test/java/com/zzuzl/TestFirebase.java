package com.zzuzl;

import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.internal.NonNull;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.Task;
import com.zzuzl.common.Firebase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestFirebase {
    @Resource
    private Firebase firebase;
    private Logger logger = LogManager.getLogger(getClass());

    @Test
    public void testLogin() {
        String token = firebase.getAuth().createCustomToken("1312312");
        logger.info(token);

        firebase.getAuth().verifyIdToken(token)
                .addOnCompleteListener(new OnCompleteListener<FirebaseToken>() {
                    public void onComplete(@NonNull Task<FirebaseToken> task) {
                        logger.info(task.isSuccessful());
                        logger.info(task.getException());
                        logger.info(task.getResult());
                    }
                });
    }
}
