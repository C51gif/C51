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
@Table("outing_record")
public class OutingRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 外出记录ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 老人ID
     */
    private Long customerId;

    /**
     * 陪同人姓名
     */
    private String accompanyPerson;

    /**
     * 陪同人电话
     */
    private String phone;

    /**
     * 外出时间
     */
    private LocalDateTime outTime;

    /**
     * 返回时间
     */
    private LocalDateTime returnTime;

    /**
     * 外出事由
     */
    private String reason;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Boolean isDeleted;


    private Boolean isReturn;

}
