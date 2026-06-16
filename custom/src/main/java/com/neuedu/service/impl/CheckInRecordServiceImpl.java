package com.neuedu.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.CheckInRecord;
import com.neuedu.entity.Customer;
import com.neuedu.mapper.CheckInRecordMapper;
import com.neuedu.mapper.CustomerMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.CheckInRecordService;
import com.neuedu.service.CustomerService;
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
public class CheckInRecordServiceImpl extends ServiceImpl<CheckInRecordMapper, CheckInRecord>  implements CheckInRecordService{

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public MyResult addCheckInRecord(CheckInRecord checkInRecord) {
        MyResult result = new MyResult();
        LocalDateTime now = LocalDateTime.now();
        checkInRecord.setCheckInDate(now);
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(Customer::getId).eq(checkInRecord.getCustomerId())
                .where(Customer::getRoomNumber).eq(checkInRecord.getBedNumber())
                .where(Customer::getIsDeleted).eq(0);
        Customer customer = customerMapper.selectOneByQuery(queryWrapper);
        if(customer != null){
            result.setCode(200);
            result.setMsg("添加成功");
            result.setData(this.save(checkInRecord));
            return result;
        }
        result.setCode(400);
        result.setData(null);
        result.setMsg("老人id与房间号不匹配");
        return result;
    }

    @Override
    public MyResult deleteCheckInRecord(Integer id) {
        MyResult myResult = new MyResult();
        MyResult my = getCheckInRecord(id);
        if(my.getCode()==200){
            boolean success = UpdateChain.of(CheckInRecord.class)
                    .set(CheckInRecord::getIsDeleted,1)
                    .where(CheckInRecord::getId).eq(id)
                    .update();
            if(success){
                myResult.setCode(200);
                myResult.setMsg("删除成功");
                myResult.setData(true);
                return myResult;
            }
            myResult.setCode(400);
            myResult.setMsg("删除失败");
            myResult.setData(false);
            return myResult;
        }
        return my;
    }


    @Override
    public MyResult getCheckInRecord(int id) {
        MyResult myResult = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("id", id);
        CheckInRecord checkInRecord = this.getOne(queryWrapper);
        if(checkInRecord != null){
            if(!checkInRecord.getIsDeleted()){
                myResult.setCode(200);
                myResult.setMsg("查找成功");
                myResult.setData(checkInRecord);
                return myResult;
            }
            myResult.setCode(408);
            myResult.setMsg("此用户已被删除");
            myResult.setData(checkInRecord);
            return myResult;
        }
        myResult.setCode(400);
        myResult.setMsg("没有此用户");
        myResult.setData(checkInRecord);
        return myResult;
    }

    @Override
    public MyResult show() {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .where(CheckInRecord::getIsDeleted).eq(0);
        List<CheckInRecord> checkInRecords = this.list(queryWrapper);
        if(checkInRecords.size()>0){
            result.setCode(200);
            result.setMsg("查找成功");
            result.setData(checkInRecords);
            return result;
        }
        result.setCode(400);
        result.setMsg("未查询到数据");
        result.setData(checkInRecords);
        return result;
    }
}
