package com.neuedu.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.Customer;
import com.neuedu.mapper.CustomerMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-09
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>  implements CustomerService{
@Autowired
private BedServiceImpl bedServiceImpl;
@Autowired
private CheckInRecordServiceImpl checkInRecordServiceImpl;
    @Override
    public MyResult add(Customer customer) {
        MyResult myResult = new MyResult();
        MyResult result = this.findbyid_card(customer.getIdCard());
        if(result.getCode()==200){
            myResult.setCode(400);
            myResult.setMsg("已有此用户");
            myResult.setData(null);
            return myResult;
        }
        else{
            if(result.getCode()==408){
                myResult.setCode(408);
                myResult.setMsg(result.getMsg()+"请手动添加该用户");
                myResult.setData(null);
                return myResult;
            }
            MyResult my = bedServiceImpl.updatecount(customer.getRoomNumber());
            if(my.getCode()==200){
                myResult.setCode(200);
                myResult.setMsg("添加成功");
                myResult.setData(this.save(customer));
                return myResult;
            }
            myResult.setCode(my.getCode());
            myResult.setMsg(my.getMsg());
            myResult.setData(my.getData());
        }
        return myResult;
    }

    @Override
    public MyResult findbyid_card(String id_card) {
        MyResult myResult = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id_card", id_card);
        Customer customer = this.getOne(queryWrapper);
        if (customer != null) {
            if(customer.getIsDeleted()==1){
                myResult.setCode(408);
                myResult.setMsg("该用户已被删除,id为"+customer.getId());
                myResult.setData(false);
                return myResult;
            }
            myResult.setCode(200);
            myResult.setMsg("查找成功");
            myResult.setData(customer);
        }
        else{
            myResult.setCode(400);
            myResult.setMsg("没有此用户");
            myResult.setData(null);
        }
        return myResult;
    }

    @Override
    public MyResult findbyid(int id) {
        MyResult myResult = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id", id);
        Customer customer = this.getOne(queryWrapper);
        if (customer != null) {
            if(customer.getIsDeleted()==1){
                myResult.setCode(408);
                myResult.setMsg("该用户已被删除，id为"+customer.getId());
                myResult.setData(false);
                return myResult;
            }
            myResult.setCode(200);
            myResult.setMsg("success");
            myResult.setData(customer);
        }
        else{
            myResult.setCode(400);
            myResult.setMsg("fail");
            myResult.setData(null);
        }
        return myResult;
    }

    @Override
    public MyResult findbyid1(int id) {
        MyResult myResult = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id", id);
        Customer customer = this.getOne(queryWrapper);
        if (customer != null) {
            myResult.setCode(200);
            myResult.setMsg("success");
            myResult.setData(customer);
        }
        else{
            myResult.setCode(400);
            myResult.setMsg("fail");
            myResult.setData(null);
        }
        return myResult;
    }



    @Override
    public MyResult updateStatus(String id_card,Integer status) {
        MyResult myResult = new MyResult();
        MyResult result = this.findbyid_card(id_card);
        if(result.getCode()==200){
            boolean success = UpdateChain.of(Customer.class)
                    .set(Customer::getStatus,status)
                    .where(Customer::getIdCard).eq(id_card)
                    .update();
            if(success){
                myResult.setCode(200);
                myResult.setMsg("更改成功");
                myResult.setData(true);
                return myResult;
            }
            myResult.setCode(400);
            myResult.setMsg("fail");
            myResult.setData(false);
        } else if (result.getCode()==408) {
            myResult.setCode(408);
            myResult.setMsg("此用户已被删除");
            myResult.setData(false);
        } else{
            myResult.setCode(400);
            myResult.setMsg("没有此用户");
            myResult.setData(false);
        }
        return myResult;
    }

    @Override
    public MyResult del(String id_card) {
        MyResult myResult = new MyResult();
        MyResult result = this.findbyid_card(id_card);
        if(result.getCode()==200){
            MyResult my1 = this.updateStatus(id_card,3);
            boolean success = UpdateChain.of(Customer.class)
                    .set(Customer::getIsDeleted,1)
                    .where(Customer::getIdCard).eq(id_card)
                    .update();
            if(success){
                Customer customer = (Customer) result.getData();
                System.out.println(111);
                MyResult my = bedServiceImpl.updatecount1(customer.getRoomNumber());
                if(my.getCode()==200){
                        System.out.println(333);
                        myResult.setCode(200);
                        myResult.setMsg("成功删除");
                        myResult.setData(true);
                        return myResult;

                }
                else{
                    myResult.setCode(my.getCode());
                    myResult.setMsg(my.getMsg());
                    myResult.setData(my.getData());
                    return myResult;
                }
            }
        }
        else {
            myResult.setCode(result.getCode());
            myResult.setMsg(result.getMsg());
            myResult.setData(result.getData());
            return myResult;
        }
        return myResult;
    }
}
