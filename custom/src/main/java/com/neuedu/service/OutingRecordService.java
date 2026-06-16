package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.OutingRecord;
import com.neuedu.result.MyResult;

import java.time.LocalDateTime;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-09
 */
public interface OutingRecordService extends IService<OutingRecord> {
    public MyResult addOutingRecord(OutingRecord record);
    public MyResult updateOutingRecord(LocalDateTime now,int id);
    public MyResult findbyid(int id);
}
