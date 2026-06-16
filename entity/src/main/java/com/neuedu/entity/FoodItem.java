package com.neuedu.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

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
@Table("food_item")
public class FoodItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 食物ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 食物名称
     */
    private String name;

    /**
     * 餐别: 早餐/午餐/晚餐/加餐
     */
    private String type;

    /**
     * 热量(kcal)
     */
    private Integer calorie;

    /**
     * 逻辑删除: 0-正常, 1-已删除
     */
    private Boolean isDeleted;

}
