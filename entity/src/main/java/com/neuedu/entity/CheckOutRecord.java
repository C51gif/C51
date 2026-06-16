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
@Table("check_out_record")
public class CheckOutRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 退住记录ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 老人ID
     */
    private Long customerId;

    /**
     * 退住日期
     */
    private LocalDateTime checkOutDate;

    /**
     * 退住原因
     */
    private String reason;

    /**
     * 退费金额
     */
    private BigDecimal refundAmount;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Boolean isDeleted;

}
