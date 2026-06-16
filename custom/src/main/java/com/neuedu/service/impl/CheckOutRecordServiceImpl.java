package com.neuedu.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.CheckOutRecord;
import com.neuedu.entity.Customer;
import com.neuedu.mapper.CheckOutRecordMapper;
import com.neuedu.mapper.CustomerMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.CheckOutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-10
 */
@Service
public class CheckOutRecordServiceImpl extends ServiceImpl<CheckOutRecordMapper, CheckOutRecord>  implements CheckOutRecordService{

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Override
    public MyResult addCheckOutRecord(CheckOutRecord checkOutRecord) {
        MyResult myResult = new MyResult();
        LocalDateTime now = LocalDateTime.now();
        checkOutRecord.setCheckOutDate(now);
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(Customer::getId, checkOutRecord.getCustomerId());
        Customer customer = customerMapper.selectOneByQuery(queryWrapper);
        if(customer != null){
            if(customer.getIsDeleted()==0){
                myResult.setCode(200);
                myResult.setMsg("添加成功");
                myResult.setData(this.save(checkOutRecord));
                customerServiceImpl.del(customer.getIdCard());
                return myResult;
            }
            myResult.setCode(408);
            myResult.setMsg("此用户已被删除");
            myResult.setData(null);
            return myResult;
        }
        myResult.setCode(400);
        myResult.setMsg("没有此用户");
        myResult.setData(null);
        return myResult;
    }

    @Override
    public MyResult deleteCheckOutRecord(Integer id) {
        MyResult myResult = new MyResult();
        MyResult my = getCheckOutRecord(id);
        if(my.getCode()==200){
            boolean success = UpdateChain.of(CheckOutRecord.class)
                    .set(CheckOutRecord::getIsDeleted,1)
                    .update();
            if(success){
                myResult.setCode(200);
                myResult.setMsg("更改成功");
                myResult.setData(true);
                return myResult;
            }
            myResult.setCode(400);
            myResult.setMsg("更改失败");
            myResult.setData(false);
            return myResult;
        }
        return my;
    }

    @Override
    public MyResult getCheckOutRecord(Integer id) {
        MyResult myResult = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id", id);
        CheckOutRecord checkOutRecord = this.getOne(queryWrapper);
        if(checkOutRecord != null){
            if(checkOutRecord.getIsDeleted()){
                myResult.setCode(408);
                myResult.setMsg("此记录被删除");
                myResult.setData(checkOutRecord);
                return myResult;
            }
            myResult.setCode(200);
            myResult.setMsg("查找成功");
            myResult.setData(checkOutRecord);
            return myResult;
        }
        myResult.setCode(400);
        myResult.setMsg("查找失败");
        myResult.setData(checkOutRecord);
        return myResult;
    }

    @Override
    public MyResult show() {
        MyResult myResult = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .where(CheckOutRecord::getIsDeleted).eq(0);
        List<CheckOutRecord> checkOutRecords = this.list(queryWrapper);
        if(checkOutRecords.size()>0){
            myResult.setCode(200);
            myResult.setMsg("查找成功");
            myResult.setData(checkOutRecords);
            return myResult;
        }
        myResult.setCode(200);
        myResult.setMsg("查找失败");
        myResult.setData(checkOutRecords);
        return myResult;
    }
}
