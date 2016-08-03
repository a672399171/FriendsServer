package com.zzuzl.dao;

import com.zzuzl.model.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhanglei53 on 2016/8/1.
 */
public interface ContactDao {
    List<Contact> searchContact(long id);

    int addContact(@Param("from") String from,
                      @Param("to") String to);

    int deleteContact(String[] users);
}
