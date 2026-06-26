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
import com.neuedu.entity.Bed;
import com.neuedu.service.BedService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 *  控制层。
 *
 * @author admin
 * @since 2026-06-09
 */
@RestController
@RequestMapping("/bed")
public class BedController {

    @Autowired
    private BedService bedService;

    /**
     * 保存。
     *
     * @param bed 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Bed bed) {
        return bedService.save(bed);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return bedService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param bed 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Bed bed) {
        return bedService.updateById(bed);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Bed> list() {
        return bedService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public Bed getInfo(@PathVariable Long id) {
        return bedService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @PostMapping("page")
    public Page<Bed> page(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Integer pageNum = Integer.parseInt(String.valueOf(map.get("pageNum")));
        Integer pageSize = Integer.parseInt(String.valueOf(map.get("pageSize")));
        Page<Bed> page = new Page<>(pageNum, pageSize);
        return bedService.page(page);
    }

    @PostMapping("showpage")
    public MyResult showpage(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        int ooc = Integer.parseInt(map.get("ooc").toString());
        System.out.println(ooc);
        long pageNum = 1;
        long pageSize = 8;

        if (map.containsKey("pageNum")) {
            pageNum = Long.parseLong(map.get("pageNum").toString());
        }
        if (map.containsKey("pageSize")) {
            pageSize = Long.parseLong(map.get("pageSize").toString());
        }

        // 3. 创建 Page 对象
        Page<Bed> page = new Page<>(pageNum, pageSize);

        // 4. 调用 Service，把 occ 和 page 都传过去
        return bedService.findbyocc(ooc, page);
    }


    @GetMapping("show")
    public MyResult show() {
        return bedService.show();
    }

    @PostMapping("updatebed")
    public MyResult updateBed(@RequestBody Map<String,String> data) {
        String room_number = data.get("room_number");
        int room = Integer.parseInt(room_number);
        return bedService.updatecount(room);
    }
    @PostMapping("/findbyroom")
    public MyResult findbyroom(@RequestBody Map<String,String> data) {
        String room_number = data.get("room_number");
        int room = Integer.parseInt(room_number);
        return bedService.findbyRoom(room);
    }

}
