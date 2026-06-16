package com.neuedu.controller;

import com.mybatisflex.core.paginate.Page;
import com.neuedu.entity.CheckInRecord;
import com.neuedu.result.MyResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.neuedu.service.CheckInRecordService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *  控制层。
 *
 * @author admin
 * @since 2026-06-10
 */
@RestController
@RequestMapping("/checkInRecord")
public class CheckInRecordController {

    @Autowired
    private CheckInRecordService checkInRecordService;

    /**
     * 保存。
     *
     * @param checkInRecord 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody CheckInRecord checkInRecord) {
        return checkInRecordService.save(checkInRecord);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return checkInRecordService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param checkInRecord 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody CheckInRecord checkInRecord) {
        return checkInRecordService.updateById(checkInRecord);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<CheckInRecord> list() {
        return checkInRecordService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public CheckInRecord getInfo(@PathVariable Long id) {
        return checkInRecordService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<CheckInRecord> page(Page<CheckInRecord> page) {
        return checkInRecordService.page(page);
    }


    @GetMapping("show")
    public MyResult getShow() {
        return checkInRecordService.show();
    }
    @PostMapping("add")
    public MyResult add(@RequestBody CheckInRecord checkInRecord) {
        return checkInRecordService.addCheckInRecord(checkInRecord);
    }
    @PostMapping("del")
    public MyResult del(@RequestBody Map<String , Object> data) {
        int id = Integer.parseInt(data.get("id").toString());
        return checkInRecordService.deleteCheckInRecord(id);
    }
}
