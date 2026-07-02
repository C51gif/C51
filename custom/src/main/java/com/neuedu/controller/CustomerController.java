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
import com.neuedu.entity.Customer;
import com.neuedu.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 保存。
     *
     * @param customer 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return customerService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param customer 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Customer customer) {
        return customerService.updateById(customer);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public MyResult list() {

        MyResult myResult = new MyResult();
        List<Customer> list = customerService.list();
        myResult.setCode(200);
        myResult.setMsg("success");
        myResult.setData(list);
        return myResult;
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public Customer getInfo(@PathVariable Long id) {
        return customerService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Customer> page(Page<Customer> page) {
        return customerService.page(page);
    }


    @PostMapping("add")
    public MyResult add(@RequestBody Customer customer) {
        return customerService.add(customer);
    }
    @PostMapping("updatestatus")
    public MyResult update(@RequestBody Map<String,String> data) {
        String id_card = data.get("id_card");
        String status = data.get("status");
        Integer statu = Integer.valueOf(status);
        return customerService.updateStatus(id_card,statu);
    }
    @PostMapping("del")
    public MyResult del(@RequestBody Map<String,String> data) {
        String id_card = data.get("id_card");
        return customerService.del(id_card);
    }

    @PostMapping("findbyId")
    public MyResult findbuId(@RequestBody Map<String,String> data) {
        int id = Integer.valueOf(data.get("id"));
        return customerService.findbyid(id);
    }

    @PostMapping("findbyId1")
    public MyResult findbuId1(@RequestBody Map<String,String> data) {
        int id = Integer.valueOf(data.get("id"));
        return customerService.findbyid1(id);
    }


    @PostMapping("updateBed")
    public MyResult updateBed(@RequestBody Map<String,String> data) {
        int roomNum = Integer.valueOf(data.get("roomNum"));
        int bedNum = Integer.valueOf(data.get("bedNum"));
        int bedId = Integer.valueOf(data.get("bedId"));
        String idCard = data.get("idCard");
        return customerService.updatebed(idCard,roomNum,bedNum,bedId);

    }

    @PostMapping("show")
    public MyResult show(@RequestBody Map<String,String> data) {
        int pageNum = Integer.valueOf(data.get("pageNum"));
        int pageSize = Integer.valueOf(data.get("pageSize"));
        return customerService.show(pageNum,pageSize);
    }
}
