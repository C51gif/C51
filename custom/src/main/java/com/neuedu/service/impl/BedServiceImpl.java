package com.neuedu.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.neuedu.entity.Bed;
import com.neuedu.mapper.BedMapper;
import com.neuedu.mapper.CustomerMapper;
import com.neuedu.result.MyResult;
import com.neuedu.service.BedService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 *  服务层实现。
 *
 * @author admin
 * @since 2026-06-09
 */
@Service
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed>  implements BedService{

    private final CustomerMapper customerMapper;

    public BedServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public MyResult show() {
        MyResult result = new MyResult();
        QueryWrapper wrapper = QueryWrapper.create()
                .select()
                .from(Bed.class)
                .where(Bed::getIsOccupied).eq(0)
                .where(Bed::getIsDeleted).eq(0);

        List<Bed> beds = this.list(wrapper);
        result.setCode(200);
        result.setMsg("查找成功");
        result.setData(beds);
        return result;
    }

    @Override
    public MyResult findbyRoom(int room_number) {
        MyResult result = new MyResult();
        QueryWrapper wrapper = QueryWrapper.create()
                .select()
                .from(Bed.class)
                .where(Bed::getIsDeleted).eq(0)
                .where(Bed::getRoomNumber).eq(room_number);
        Bed bed = this.getOne(wrapper);
        if(bed == null){
            result.setCode(400);
            result.setMsg("没有此房间");
            result.setData(null);
            return result;
        }
        result.setCode(200);
        result.setMsg("查找成功");
        result.setData(bed);
        return result;
    }

    @Override
    public MyResult updateis_occ(int room_number) {
        MyResult result = new MyResult();
        boolean success = UpdateChain.of(Bed.class)
                .set(Bed::getIsOccupied,1)
                .where(Bed::getRoomNumber).eq(room_number)
                .update();
        if(success){
            result.setCode(200);
            result.setMsg("更改成功");
            result.setData(true);
            return result;
        }
        result.setCode(400);
        result.setMsg("更改失败");
        result.setData(false);
        return result;
    }


    @Override
    public MyResult updateis_occ1(int room_number) {
        MyResult result = new MyResult();
        boolean success = UpdateChain.of(Bed.class)
                .set(Bed::getIsOccupied,0)
                .where(Bed::getRoomNumber).eq(room_number)
                .update();
        if(success){
            result.setCode(200);
            result.setMsg("更改成功");
            result.setData(true);
            return result;
        }
        result.setCode(400);
        result.setMsg("更改失败");
        result.setData(false);
        return result;
    }


    @Override
    public MyResult updatecount(int room_number) {
        MyResult result = new MyResult();

        MyResult updateResult = findbyRoom(room_number);
        if(updateResult.getCode()==400){
            result.setCode(400);
            result.setMsg(updateResult.getMsg());
            result.setData(updateResult.getData());
            return result;
        }
        else if(updateResult.getCode()==200){
            Bed bed = (Bed) updateResult.getData();
            if(bed.getCount()<bed.getMaxCount()){
                boolean success = UpdateChain.of(Bed.class)
                        .setRaw(Bed::getCount,"count+1")
                        .where(Bed::getRoomNumber).eq(room_number)
                        .update();
                //如果成功
                if(success){
                    //更改宿舍状态
                    MyResult my = findbyRoom(room_number);
                    Bed bed1 = (Bed) my.getData();
                    if(bed1.getCount()>=bed.getMaxCount()){
                        updateis_occ(room_number);
                    }

                    result.setCode(200);
                    result.setMsg("更改成功");
                    result.setData(success);

                    return result;
                }
            }
            else{
                result.setCode(400);
                result.setMsg("房间已满");
                result.setData(null);
                return result;
            }
        }
        result.setCode(400);
        result.setMsg("系统出错");
        result.setData(null);
        return result;
    }
    @Override
    public MyResult updatecount1(int room_number) {
        MyResult result = new MyResult();

        MyResult updateResult = findbyRoom(room_number);
        if(updateResult.getCode()==400){
            result.setCode(400);
            result.setMsg(updateResult.getMsg());
            result.setData(updateResult.getData());
            return result;
        }
        else if(updateResult.getCode()==200){
            Bed bed = (Bed) updateResult.getData();
            if(bed.getCount()>0){
                boolean success = UpdateChain.of(Bed.class)
                        .setRaw(Bed::getCount,"count-1")
                        .where(Bed::getRoomNumber).eq(room_number)
                        .update();
                //如果成功
                if(success){
                    //更改宿舍状态
                    MyResult my = findbyRoom(room_number);
                    Bed bed1 = (Bed) my.getData();
                    if(bed1.getCount()<bed.getMaxCount()){
                        updateis_occ1(room_number);
                    }

                    result.setCode(200);
                    result.setMsg("更改成功");
                    result.setData(success);

                    return result;
                }
            }
            else{
                result.setCode(400);
                result.setMsg("房间已空");
                result.setData(null);
                return result;
            }
        }
        result.setCode(400);
        result.setMsg("系统出错");
        result.setData(null);
        return result;
    }

    @Override
    public MyResult findbyocc(int occ,Page<Bed> page)
    {
        MyResult result = new MyResult();
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(Bed::getIsOccupied).eq(occ);
        Page<Bed> beds = this.page(page, queryWrapper);
        result.setCode(200);
        result.setMsg("111111");
        result.setData(beds);
        return result;
    }
}
