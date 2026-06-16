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
 * @since 2026-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("customer")
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 老人ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别: 0女 1男
     */
    private Boolean gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 状态: 1在住 2请假 3退住
     */
    private Integer status;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Integer isDeleted;

    private LocalDateTime createTime;

    private Integer roomNumber;

    private Integer bedNumber;

}
