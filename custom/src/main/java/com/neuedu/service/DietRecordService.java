package com.neuedu.service;

import com.mybatisflex.core.service.IService;
import com.neuedu.entity.DietRecord;
import com.neuedu.result.MyResult;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-10
 */
public interface DietRecordService extends IService<DietRecord> {
public MyResult addDietRecord(DietRecord dietRecord);
}
