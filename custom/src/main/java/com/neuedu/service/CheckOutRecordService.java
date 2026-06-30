package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.CheckOutRecord;
import com.neuedu.result.MyResult;

import java.util.Map;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-10
 */
public interface CheckOutRecordService extends IService<CheckOutRecord> {
public MyResult addCheckOutRecord(CheckOutRecord checkOutRecord);
public MyResult deleteCheckOutRecord(Integer id);
public MyResult getCheckOutRecord(Integer id);

    MyResult show(int pageNum, int pageSize);
}
