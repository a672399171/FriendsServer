package com.zzuzl.dao;

import com.zzuzl.model.Admin;

/**
 * Created by zhanglei53 on 2016/8/4.
 */
public interface AdminDao {
    Admin search(String username);
}
