package com.neuedu.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author admin
 * @since 2026-06-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("check_in_record")
public class CheckInRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入住记录ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 老人ID
     */
    private Long customerId;

    /**
     * 分配床位ID
     */
    private Integer bedNumber;

    /**
     * 责任管家ID(关联sys_user)
     */
    private Long caregiverId;

    /**
     * 入住日期
     */
    private LocalDateTime checkInDate;

    /**
     * 合同时长(月)
     */
    private Integer contractMonths;

    /**
     * 押金金额
     */
    private BigDecimal deposit;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Boolean isDeleted;

}
