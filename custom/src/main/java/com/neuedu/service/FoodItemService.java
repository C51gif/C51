package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.FoodItem;
import com.neuedu.result.MyResult;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-10
 */
public interface FoodItemService extends IService<FoodItem> {
public MyResult deleteById(int id);
public MyResult findById(int id);
public MyResult show();
public MyResult restore(int id);
public MyResult add(FoodItem foodItem);
}
