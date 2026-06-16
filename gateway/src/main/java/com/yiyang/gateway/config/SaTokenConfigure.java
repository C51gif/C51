package com.yiyang.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfigure {

    /**
     * 注册 Sa-Token 全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/sysUser/login", "/sysUser/register", "/favicon.ico")


                // [3] 认证函数
                .setAuth(obj -> {
                    // --- 【新增代码开始】打印请求头信息 ---
                    // 获取当前请求对象
                    SaRequest req = SaHolder.getRequest();
                    // 打印所有 Header (注意：这可能会打印很多内容，调试完记得删掉)
                    System.out.println(">>> [网关调试] 收到请求: " + req.getUrl());
                    // 专门打印 satoken 的值
                    String tokenValue = req.getHeader("satoken");
                    System.out.println(">>> [网关调试] 提取到的 satoken 值为: [" + tokenValue + "]");
                    // --- 【新增代码结束】 ---


                    // 核心逻辑：校验当前请求是否已经登录
                    StpUtil.checkLogin();
                })

                // [4] 异常处理函数
                .setError(e -> {
                    // --- 【新增代码】如果报错，也打印一下 ---
                    System.out.println(">>> [网关报错] 错误原因: " + e.getMessage());
                    // ------------------------------------

                    return SaResult.error("认证失败：" + e.getMessage()).setCode(401);
                });
    }
}