package com.neuedu.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.neuedu.entity.Bed;
import com.neuedu.result.MyResult;

/**
 *  服务层。
 *
 * @author admin
 * @since 2026-06-09
 */
public interface BedService extends IService<Bed> {
    public MyResult show();
    public MyResult findbyRoom(int room_number);
    public MyResult updateis_occ(int room_number);

    MyResult updateis_occ2(int room_number);

    MyResult updateis_occ1(int room_number);

    public MyResult updatecount(int room_number);

    MyResult updatecount1(int room_number);
    public MyResult findbyocc(int occ, Page<Bed> page);
}
