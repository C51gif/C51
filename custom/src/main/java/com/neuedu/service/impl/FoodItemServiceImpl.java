package com.neuedu.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.FoodItem;
import com.neuedu.mapper.FoodItemMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.FoodItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-10
 */
@Service
public class FoodItemServiceImpl extends ServiceImpl<FoodItemMapper, FoodItem>  implements FoodItemService{

    @Override
    public MyResult deleteById(int id) {
        MyResult result = new MyResult();
        MyResult my = findById(id);
        if(my.getCode()==200){
            boolean success = UpdateChain.of(FoodItem.class)
                    .set(FoodItem::getIsDeleted,1)
                    .where(FoodItem::getId).eq(id)
                    .update();
            if(success){
                result.setCode(200);
                result.setMsg("删除成功");
                result.setData(true);
                return result;
            }
        }
        return my;
    }

    @Override
    public MyResult findById(int id) {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = new QueryWrapper()
                .eq("id", id);
        FoodItem food =  this.getOne(queryWrapper);
        if(food!=null){
            if(food.getIsDeleted()){
                result.setCode(408);
                result.setMsg("此食物已被删除,id为"+id);
                result.setData(false);
            }
            result.setCode(200);
            result.setMsg("查找成功");
            result.setData(food);
            return result;
        }
        result.setCode(400);
        result.setMsg("查找失败");
        result.setData(food);
        return result;
    }

    @Override
    public MyResult show() {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .where(FoodItem::getIsDeleted).eq(0);
        List<FoodItem> foodItems = this.list(queryWrapper);
        if(foodItems!=null&&foodItems.size()>0){
            result.setCode(200);
            result.setMsg("查找成功");
            result.setData(foodItems);
            return result;
        }
        result.setCode(400);
        result.setMsg("暂无食物");
        result.setData(foodItems);
        return result;
    }

    @Override
    public MyResult restore(int id) {
        MyResult result = new MyResult();
        MyResult my = findById(id);
        if(my.getCode()==200){
            boolean success = UpdateChain.of(FoodItem.class)
                    .set(FoodItem::getIsDeleted,0)
                    .update();
            if(success){
                result.setCode(200);
                result.setMsg("恢复成功");
                result.setData(true);
            }
            result.setCode(400);
            result.setMsg("恢复失败");
            result.setData(false);
        }
        return my;
    }

    @Override
    public MyResult add(FoodItem foodItem) {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = new QueryWrapper()
                .eq(FoodItem::getName, foodItem.getName());
        FoodItem food =  this.getOne(queryWrapper);
        if(food==null){
            result.setCode(200);
            result.setMsg("添加成功");
            result.setData(true);
            return result;
        }
        result.setCode(400);
        result.setMsg("已有此食物，id为"+food.getId()+",请恢复");
        result.setData(false);
        return result;
    }


}
