package com.tong.shf.service;

import com.tong.shf.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 10:09
 */
public interface HouseImageService extends BaseService<HouseImage> {
    List<HouseImage> findListByHouseIdAndType(long houseId,Integer type);
}
