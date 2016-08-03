package com.zzuzl.service;

import com.zzuzl.dto.Result;
import com.zzuzl.model.Contact;

/**
 * Created by zhanglei53 on 2016/8/3.
 */
public interface ContactService {
    Result<Contact> searchContact(long id);

    Result addContact(String from,String to);

    Result deleteContact(String[] users);
}
