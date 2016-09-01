package com.zzuzl.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.internal.NonNull;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;
import com.zzuzl.common.Constants;
import com.zzuzl.common.Firebase;
import com.zzuzl.common.annotaion.Authorization;
import com.zzuzl.dto.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private Firebase firebase;
    private Logger logger = LogManager.getLogger(getClass());
    private boolean flag = false;

    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");

        if (handler instanceof HandlerMethod) {
            final HandlerMethod method = (HandlerMethod) handler;
            Authorization auth = method.getMethodAnnotation(Authorization.class);
            flag = false;
            Object lock = new Object();

            if (auth != null) {
                String value = auth.value();
                String token = request.getHeader(Constants.TOKEN);
                if (value.equals(Constants.AUTH_USER) && token != null) {
                    synchronized (lock) {
                        firebase.getAuth().verifyIdToken(token)
                                .addOnCompleteListener(new MyListener(lock));
                        lock.wait();
                    }
                }

                if (!flag) {
                    Result result = new Result();
                    result.setSuccess(false);
                    result.setError("未认证");

                    response.getWriter().println(new ObjectMapper().writeValueAsString(result));
                    // response.sendError(401, "未认证");
                }

                return flag;
            }
        }

        return true;
    }

    class MyListener implements OnCompleteListener<FirebaseToken> {
        private Object lock;

        MyListener(Object lock) {
            this.lock = lock;
        }

        public void onComplete(@NonNull Task<FirebaseToken> task) {
            synchronized (lock) {
                flag = task.isSuccessful();
                if (flag) {
                    logger.info(task.getResult().getUid());
                    task.getResult().getClaims();
                } else {
                    logger.info("验证不通过");
                }
                lock.notify();
            }
        }
    }
}
