package com.tong.shf.service;

import com.tong.shf.entity.Admin;
import com.tong.shf.entity.HouseBroker;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 10:32
 */
public interface HouseBrokerService extends BaseService<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long houseId);

    List<Admin> findHouseOtherBroker(long houseId);
}
