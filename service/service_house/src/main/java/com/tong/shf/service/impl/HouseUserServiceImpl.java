package com.tong.shf.service.impl;

import com.tong.shf.entity.HouseUser;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.HouseUserMapper;
import com.tong.shf.service.BaseService;
import com.tong.shf.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 11:27
 */
@DubboService
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {
     @Autowired
     private HouseUserMapper houseUserMapper;

    @Override
    public BaseMapper<HouseUser> getEntityMapper() {
        return houseUserMapper;
    }

    @Override
    public List<HouseUser> findListByHouseId(Long houseId) {
        return houseUserMapper.findListByHouseId(houseId);
    }

}
