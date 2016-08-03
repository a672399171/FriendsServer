package com.zzuzl.controller;

import com.zzuzl.dto.Result;
import com.zzuzl.model.Contact;
import com.zzuzl.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhanglei53 on 2016/8/3.
 */
@Controller
@RequestMapping("contact")
public class ContactController {
    @Resource
    private ContactService contactService;

    @RequestMapping("/")
    @ResponseBody
    public Result<Contact> listContacts(@RequestParam(required = false, defaultValue = "0") Long id) {
        return contactService.searchContact(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addContact(String from, String to) {
        return contactService.addContact(from, to);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteContact(String from, String to) {
        return contactService.deleteContact(new String[]{from, to});
    }
}
