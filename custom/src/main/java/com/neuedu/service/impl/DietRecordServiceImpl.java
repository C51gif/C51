package com.neuedu.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.DietRecord;
import com.neuedu.mapper.DietRecordMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.DietRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-10
 */
@Service
public class DietRecordServiceImpl extends ServiceImpl<DietRecordMapper, DietRecord>  implements DietRecordService{
@Autowired
private CustomerServiceImpl customerServiceImpl;
@Autowired
private FoodItemServiceImpl foodItemServiceImpl;
    @Override
    public MyResult addDietRecord(DietRecord dietRecord) {
        LocalDateTime now = LocalDateTime.now();
        dietRecord.setMealTime(now);
        MyResult result = new MyResult();
        MyResult my1 = customerServiceImpl.findbyid(Math.toIntExact(dietRecord.getCustomerId()));
        MyResult my2 = foodItemServiceImpl.findById(Math.toIntExact(dietRecord.getFoodId()));
        if(my1.getCode()==200){
            if(my2.getCode()==200){
                result.setCode(200);
                result.setMsg("添加成功");
                result.setData(dietRecord);
                return result;
            }
            return my2;
        }
        return my1;
    }
}
