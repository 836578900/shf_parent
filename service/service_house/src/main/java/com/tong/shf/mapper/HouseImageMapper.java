package com.tong.shf.mapper;

import com.tong.shf.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 10:13
 */
public interface HouseImageMapper extends BaseMapper<HouseImage> {
    List<HouseImage> findListByHouseIdAndType(@Param("houseId") long houseId,@Param("type") Integer type);
}
