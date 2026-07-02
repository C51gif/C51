package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.Customer;
import com.neuedu.result.MyResult;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-09
 */
public interface CustomerService extends IService<Customer> {
    public MyResult add(Customer customer);
    public MyResult findbyid_card(String id_card);

    public MyResult findbyid(int id);

    MyResult findbyid1(int id);

    public MyResult updateStatus(String id_card, Integer status);

    public MyResult del(String id_card);

    MyResult updatebed(String idCard, int roomNum, int bedNum, int bedId);

    MyResult show(int pageNum, int pageSize);
}
