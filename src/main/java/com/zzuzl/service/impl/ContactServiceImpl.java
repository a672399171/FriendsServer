package com.zzuzl.service.impl;

import com.zzuzl.dao.ContactDao;
import com.zzuzl.dto.Result;
import com.zzuzl.model.Contact;
import com.zzuzl.service.ContactService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhanglei53 on 2016/8/3.
 */
@Service("contactService")
public class ContactServiceImpl implements ContactService {
    @Resource
    private ContactDao contactDao;

    public Result<Contact> searchContact(long id) {
        Result<Contact> result = new Result<Contact>();
        result.setList(contactDao.searchContact(id));

        return result;
    }

    public Result addContact(String from,String to) {
        Result result = new Result();
        if(contactDao.addContact(from, to) < 1) {
            result.setSuccess(false);
            result.setError("添加失败");
        }
        return result;
    }

    public Result deleteContact(String[] users) {
        Result result = new Result();
        if(contactDao.deleteContact(users) < 1) {
            result.setSuccess(false);
            result.setError("删除失败");
        }
        return result;
    }
}
