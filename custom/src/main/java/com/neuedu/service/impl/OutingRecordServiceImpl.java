package com.neuedu.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.Customer;
import com.neuedu.entity.OutingRecord;
import com.neuedu.mapper.OutingRecordMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.OutingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-09
 */
@Service
public class OutingRecordServiceImpl extends ServiceImpl<OutingRecordMapper, OutingRecord>  implements OutingRecordService{
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Override
    public MyResult addOutingRecord(OutingRecord record) {
        MyResult result = new MyResult();
        Customer customer = (Customer) customerServiceImpl.findbyid(Math.toIntExact(record.getCustomerId())).getData();
        if(customer != null){
            if(this.save(record)){
                customerServiceImpl.updateStatus(customer.getIdCard(),2);
                result.setCode(200);
                result.setMsg("success");
                result.setData(record);
                return result;
            }
            result.setCode(400);
            result.setMsg("添加失败");
            return result;
        }
        result.setCode(400);
        result.setMsg("没有此老人");
        return result;

    }

    @Override
    public MyResult updateOutingRecord(LocalDateTime now, int id) {
        MyResult result = new MyResult();
        MyResult my = findbyid(id);
        if(my.getCode() == 200){
            OutingRecord record = (OutingRecord) my.getData();
            MyResult my1 = customerServiceImpl.findbyid(Math.toIntExact(record.getCustomerId()));
            if(my1.getCode() == 200){
                Customer customer = (Customer) my1.getData();
                boolean success = UpdateChain.of(OutingRecord.class)
                        .set(OutingRecord::getReturnTime,now)
                        .where(OutingRecord::getId).eq(id)
                        .update();
                if(success){
                    customerServiceImpl.updateStatus(customer.getIdCard(),1);
                    result.setCode(200);
                    result.setMsg("登记成功");
                    result.setData(true);
                    return result;
                }
                result.setCode(400);
                result.setMsg("登记失败");
                result.setData(null);
                return result;
            }
            return my1;
        }
        return my;
    }

    @Override
    public MyResult findbyid(int id) {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id", id);
        OutingRecord record = this.getOne(queryWrapper);
        if(record != null){
            result.setCode(200);
            result.setMsg("查找到记录");
            result.setData(record);
            return result;
        }
        result.setCode(400);
        result.setMsg("查无记录");
        result.setData(null);
        return result;
    }
}
