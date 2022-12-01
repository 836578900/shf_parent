package com.tong.shf.service.impl;

import com.tong.shf.entity.Admin;
import com.tong.shf.entity.HouseBroker;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.HouseBrokerMapper;
import com.tong.shf.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 10:32
 */
@DubboService
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {
    @Autowired
    private HouseBrokerMapper houseBrokerMapper;
    @Override
    public BaseMapper<HouseBroker> getEntityMapper() {
        return houseBrokerMapper;
    }


    @Override
    public List<HouseBroker> findListByHouseId(Long houseId) {

        return houseBrokerMapper.findListByHouseId(houseId);
    }

    @Override
    public List<Admin> findHouseOtherBroker(long houseId) {
        List<HouseBroker> brokerList = houseBrokerMapper.findListByHouseId(houseId);
        ArrayList<Long> ids = new ArrayList<>();
        for (HouseBroker houseBroker : brokerList) {
            ids.add(houseBroker.getBrokerId());
        }
        //需要查询admin表格，并且排除上面集合中的经济人
        return houseBrokerMapper.findHouseOtherBroker(ids);
    }
}
