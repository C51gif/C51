package com.neuedu.controller;

import com.mybatisflex.core.paginate.Page;
import com.neuedu.result.MyResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.neuedu.entity.FoodItem;
import com.neuedu.service.FoodItemService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 *  控制层。
 *
 * @author admin
 * @since 2026-06-10
 */
@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    /**
     * 保存。
     *
     * @param foodItem 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public MyResult save(@RequestBody FoodItem foodItem) {
        MyResult result = new MyResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(foodItemService.save(foodItem));
        return result;
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return foodItemService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param foodItem 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody FoodItem foodItem) {
        return foodItemService.updateById(foodItem);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public MyResult list() {
        MyResult result = new MyResult();
        result.setData(foodItemService.list());
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public FoodItem getInfo(@PathVariable Long id) {
        return foodItemService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<FoodItem> page(Page<FoodItem> page) {
        return foodItemService.page(page);
    }

    @PostMapping("del")
    public MyResult del(@RequestBody Map<String, Object> data) {
        Integer id = (Integer) data.get("id");
        return foodItemService.deleteById(id);
    }

    @GetMapping("show")
    public MyResult show() {
        return foodItemService.show();
    }

    @PostMapping("add")
    public MyResult add(@RequestBody FoodItem foodItem) {
        return foodItemService.add(foodItem);
    }

}
