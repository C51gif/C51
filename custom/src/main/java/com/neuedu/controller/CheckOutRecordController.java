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
import com.neuedu.entity.CheckOutRecord;
import com.neuedu.service.CheckOutRecordService;
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
@RequestMapping("/checkOutRecord")
public class CheckOutRecordController {

    @Autowired
    private CheckOutRecordService checkOutRecordService;

    /**
     * 保存。
     *
     * @param checkOutRecord 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody CheckOutRecord checkOutRecord) {
        return checkOutRecordService.save(checkOutRecord);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return checkOutRecordService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param checkOutRecord 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody CheckOutRecord checkOutRecord) {
        return checkOutRecordService.updateById(checkOutRecord);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<CheckOutRecord> list() {
        return checkOutRecordService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public CheckOutRecord getInfo(@PathVariable Long id) {
        return checkOutRecordService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<CheckOutRecord> page(Page<CheckOutRecord> page) {
        return checkOutRecordService.page(page);
    }

    @PostMapping("show")
    public MyResult show(@RequestBody Map<String, Object> map) {
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        return checkOutRecordService.show(pageNum, pageSize);
    }
    @PostMapping("add")
    public MyResult add(@RequestBody CheckOutRecord checkOutRecord) {
        System.out.println("🔥 接收到的对象: " + checkOutRecord);
        System.out.println("🔥 客户ID: " + checkOutRecord.getCustomerId());
        return checkOutRecordService.addCheckOutRecord(checkOutRecord);
    }
    @PostMapping("del")
    public MyResult del(@RequestBody Map<String , Object> data) {
        int id = Integer.parseInt(data.get("id").toString());
        return checkOutRecordService.deleteCheckOutRecord(id);
    }

}
