package com.tong.shf.mapper;

import com.tong.shf.entity.House;

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
}
