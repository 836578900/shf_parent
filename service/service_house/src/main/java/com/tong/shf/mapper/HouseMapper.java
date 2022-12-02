package com.tong.shf.mapper;

import com.tong.shf.entity.House;
import com.tong.shf.vo.HouseQueryVo;
import com.tong.shf.vo.HouseVo;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 19:17
 */
public interface HouseMapper extends BaseMapper<House> {
    void publish(House house);
    List<HouseVo> findListPage(HouseQueryVo houseQueryVo);
}
