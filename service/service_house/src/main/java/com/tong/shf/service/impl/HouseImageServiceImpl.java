package com.tong.shf.service.impl;

import com.tong.shf.entity.HouseImage;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.HouseImageMapper;
import com.tong.shf.service.BaseService;
import com.tong.shf.service.HouseImageService;
import com.tong.shf.service.HouseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 10:10
 */
@DubboService
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {
    @Autowired
    private HouseImageMapper houseImageMapper;
    @Override
    public BaseMapper<HouseImage> getEntityMapper() {
        return houseImageMapper;
    }

    @Override
    public List<HouseImage> findListByHouseIdAndType(long houseId, Integer type) {
        return houseImageMapper.findListByHouseIdAndType(houseId,type);
    }
}
