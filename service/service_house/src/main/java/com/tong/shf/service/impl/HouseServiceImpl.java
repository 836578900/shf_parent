package com.tong.shf.service.impl;

import com.tong.shf.entity.House;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.DictMapper;
import com.tong.shf.mapper.HouseMapper;
import com.tong.shf.service.HouseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 19:15
 */
@DubboService
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private DictMapper dictMapper;

    @Override
    public BaseMapper<House> getEntityMapper() {
        return houseMapper;
    }

    @Override
    public void publish(long houseId, Integer status) {
        House house = new House();
        house.setStatus(status);
        house.setId(houseId);
        houseMapper.publish(house);
    }

    @Override
    public House getById(Serializable id) {
        House house = houseMapper.getById(id);
        house.setHouseTypeName(dictMapper.getById(house.getHouseTypeId()).getName());
        house.setFloorName(dictMapper.getById(house.getFloorId()).getName());
        house.setBuildStructureName(dictMapper.getById(house.getBuildStructureId()).getName());
        house.setDirectionName(dictMapper.getById(house.getDirectionId()).getName());
        house.setDecorationName(dictMapper.getById(house.getDecorationId()).getName());
        house.setHouseUseName(dictMapper.getById(house.getHouseUseId()).getName());
        return house;
    }
}
