package com.neuedu.controller;

import com.mybatisflex.core.paginate.Page;
import com.neuedu.result.MyResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.neuedu.entity.OutingRecord;
import com.neuedu.service.OutingRecordService;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  控制层。
 *
 * @author admin
 * @since 2026-06-09
 */
@RestController
@RequestMapping("/outingRecord")
public class OutingRecordController {

    @Autowired
    private OutingRecordService outingRecordService;

    /**
     * 保存。
     *
     * @param outingRecord 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody OutingRecord outingRecord) {
        return outingRecordService.save(outingRecord);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return outingRecordService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param outingRecord 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody OutingRecord outingRecord) {
        return outingRecordService.updateById(outingRecord);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<OutingRecord> list() {
        return outingRecordService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public OutingRecord getInfo(@PathVariable Long id) {
        return outingRecordService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @PostMapping("page")
    public Page<OutingRecord> page(@RequestBody Map<String,Object> map) {
        Integer pageNum = Integer.parseInt(String.valueOf(map.get("pageNum")));
        Integer pageSize = Integer.parseInt(String.valueOf(map.get("pageSize")));
        Page<OutingRecord> page = new Page<>(pageNum, pageSize);
        return outingRecordService.page(page);
    }


    @PostMapping("addOuting")
    public MyResult addOuting(@RequestBody OutingRecord outingRecord) {
        LocalDateTime now = LocalDateTime.now();
        outingRecord.setOutTime(now);
        outingRecord.setReturnTime(null);
        return outingRecordService.addOutingRecord(outingRecord);
    }

    @PostMapping("returnOuting")
    public MyResult returnOuting(@RequestBody Map<String, Object> data) {
        LocalDateTime now = LocalDateTime.now();
        Integer id = (Integer) data.get("id");
        return outingRecordService.updateOutingRecord(now,id);
    }

    @PostMapping("findByReturn")
    public MyResult findByReturn(@RequestBody Map<String, Object> map) {
        int pageNum = Integer.parseInt(String.valueOf(map.get("pageNum")));
        int pageSize = Integer.parseInt(String.valueOf(map.get("pageSize")));
        int isReturn = Integer.parseInt(String.valueOf(map.get("isReturn")));
        return outingRecordService.findbyReturn(isReturn,pageNum,pageSize);
    }

}
