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
 * @since 2026-06-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("diet_record")
public class DietRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 老人ID
     */
    private Long customerId;

    /**
     * 食物ID
     */
    private Long foodId;

    /**
     * 用餐时间
     */
    private LocalDateTime mealTime;

    /**
     * 进食情况: 1-全部吃完, 2-吃了一半, 3-未吃
     */
    private Boolean intakeStatus;

    /**
     * 备注(如: 食欲不佳)
     */
    private String remark;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Boolean isDeleted;

    private Long userId;

}
