package com.neuedu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.SysUser;
import com.neuedu.mapper.SysUserMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>  implements SysUserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public MyResult getbyphone(String phone) {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("phone",phone);
        SysUser sysUser = this.getOne(queryWrapper);
        if(sysUser!=null){
            result.setCode(200);
            result.setMsg("查找成功");
            result.setData(sysUser);
        }
        else {
            result.setCode(500);
            result.setMsg("未查找到该用户");
            result.setData(null);
        }
        return result;
    }


    @Override
    public MyResult Login(String phone, String password) {
        MyResult result = new MyResult();
        SysUser sysUser = (SysUser) getbyphone(phone).getData();
        if(sysUser==null){
            result.setCode(500);
            result.setMsg("账号错误");
            result.setData(false);
            return result;
        } else {
            if(passwordEncoder.matches(password, sysUser.getPassword())){

                if(sysUser.getIsDeleted()==1)
                {
                    result.setCode(500);
                    result.setMsg("账号已被封禁");
                    result.setData(false);
                    return result;
                }

                StpUtil.login(sysUser.getId());

                String token = StpUtil.getTokenValue();
                System.out.println(token);
                result.setCode(200);
                result.setMsg("登陆成功");
                result.setData(token);
                return result;
            }
            result.setCode(500);
            result.setMsg("密码错误");
            result.setData(false);
        }
        return result;
    }

    @Override
    public MyResult register(SysUser sys) {
        System.out.println(sys);
        MyResult result = new MyResult();
        MyResult sysUserResult = getbyphone(sys.getPhone());
        if(sysUserResult.getCode()==200){
            System.out.println(sysUserResult);
            result.setCode(500);
            result.setMsg("已有该用户");
            result.setData(null);
            return result;
        }
        String password = passwordEncoder.encode(sys.getPassword());
        sys.setPassword(password);
        result.setCode(200);
        result.setMsg("注册成功");
        result.setData(this.save(sys));
        return result;
    }

}
