package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.SysUser;
import com.neuedu.result.MyResult;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-03
 */
public interface SysUserService extends IService<SysUser> {

    MyResult getbyphone(String phone);

    MyResult Login(String phone, String password);

    MyResult register(SysUser sys);
}
