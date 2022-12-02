package com.tong.shf.service;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.House;
import com.tong.shf.vo.HouseQueryVo;
import com.tong.shf.vo.HouseVo;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 19:15
 */
public interface HouseService extends BaseService<House> {
    void publish(long houseId,Integer status);
    PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo);
}
