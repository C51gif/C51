package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.CheckInRecord;
import com.neuedu.result.MyResult;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-10
 */
public interface CheckInRecordService extends IService<CheckInRecord> {
    public MyResult addCheckInRecord(CheckInRecord checkInRecord);
    public MyResult deleteCheckInRecord(Integer id);
    public MyResult getCheckInRecord(int id);
    public MyResult show();
}
