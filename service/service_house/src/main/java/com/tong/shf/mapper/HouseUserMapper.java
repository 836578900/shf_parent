package com.tong.shf.mapper;

import com.tong.shf.entity.HouseUser;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 11:36
 */
public interface HouseUserMapper extends BaseMapper<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}
