package com.neuedu.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigDecimal;

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
@Table("bed")
public class Bed implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 床位ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 房间号
     */
    private Integer roomNumber;

    /**
     * 床位类型: 单人间/双人间/多人间
     */
    private String bedType;

    /**
     * 床位费(元/月)
     */
    private BigDecimal price;

    /**
     * 占用状态: 0-空闲, 1-占用
     */
    private int isOccupied;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Boolean isDeleted;

    private Integer maxCount;

    private Integer count;

}
