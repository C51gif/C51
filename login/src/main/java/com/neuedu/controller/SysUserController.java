package com.neuedu.controller;

import com.mybatisflex.core.paginate.Page;
import com.neuedu.entity.SysUser;
import com.neuedu.result.MyResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.neuedu.service.SysUserService;


import java.util.Map;

/**
 *  控制层。
 *
 * @author admin
 * @since 2026-06-03
 */
//@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * 添加。
     *
     * @param sysUser 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SysUser sysUser) {
        return sysUserService.save(sysUser);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return sysUserService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param sysUser 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SysUser sysUser) {
        return sysUserService.updateById(sysUser);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public MyResult list() {

        MyResult result = new MyResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(sysUserService.list());

        return result;
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public SysUser getInfo(@PathVariable Long id) {
        return sysUserService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SysUser> page(Page<SysUser> page) {
        return sysUserService.page(page);
    }

    @PostMapping("/login")
    public MyResult login(@RequestBody Map<String,String> data) {
        String phone = data.get("phone");
        String password = data.get("password");
        MyResult result = sysUserService.Login(phone, password);
        return result;
    }
    @PostMapping("/register")
    public MyResult register(@RequestBody SysUser sysUser) {
        return sysUserService.register(sysUser);
    }

}
