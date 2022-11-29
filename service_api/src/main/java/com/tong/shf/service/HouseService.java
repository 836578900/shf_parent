package com.tong.shf.service;

import com.tong.shf.entity.House;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 19:15
 */
public interface HouseService extends BaseService<House> {
    void publish(long houseId,Integer status);
}
