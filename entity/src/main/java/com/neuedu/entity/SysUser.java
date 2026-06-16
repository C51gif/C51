package com.neuedu.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author admin
 * @since 2026-06-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sys_user")
public class SysUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 手机号（登录账号）
     */
    private String phone;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 0女 1男
     */
    private Integer gender;

    /**
     * 角色类型: 0-管理员, 1-医护, 2-管家, 3-家属
     */
    private Integer roleType;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 0未删除 1已删除
     */
    private Integer isDeleted;

}
