package com.tong.shf.mapper;

import com.tong.shf.entity.Admin;
import com.tong.shf.entity.HouseBroker;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 10:34
 */
public interface HouseBrokerMapper extends BaseMapper<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long houseId);
    List<Admin> findHouseOtherBroker(List<Long> ids);
}
