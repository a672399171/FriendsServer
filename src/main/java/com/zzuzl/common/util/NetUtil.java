package com.zzuzl.common.util;

import com.zzuzl.dto.Result;
import com.zzuzl.model.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhanglei53 on 2016/8/2.
 */
public class NetUtil {

    public static Result connect(String url, String schoolNum, String password, String selec) {
        Result result = new Result();
        String nianji = schoolNum.substring(0, 4);
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .timeout(10 * 1000)
                    .data("nianji", nianji)
                    .data("xuehao", schoolNum)
                    .data("mima", password)
                    .data("selec", selec)
                    .post();
        } catch (IOException e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
            e.printStackTrace();
        }

        if (document != null) {
            Elements elements = document.body().select(selec);
            if (elements != null || elements.size() < 1) {
                User user = new User();
                user.setGrade(nianji);
                user.setSchoolNum(schoolNum);

                String name = document.body().select("tr").eq(1).select("td").eq(0).text();
                String sex = document.body().select("tr").eq(3).select("td").eq(0).text();
                String classCode = document.body().select("tr").eq(8).select("td").eq(0).text();
                user.setName(name.substring(name.indexOf('：') + 1));
                user.setSex(sex.substring(sex.indexOf('：') + 1));
                user.setClassCode(classCode.substring(classCode.indexOf('：') + 1));

                result.getData().put("user",user);
            } else {
                result.setSuccess(false);
                result.setError("学号或密码错误");
            }
        } else {
            result.setSuccess(false);
            result.setError("连接错误");
        }

        return result;
    }
}
