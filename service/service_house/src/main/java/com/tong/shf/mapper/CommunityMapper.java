package com.tong.shf.mapper;

import com.tong.shf.entity.Community;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 16:21
 */
public interface CommunityMapper extends BaseMapper<Community> {
    List<Community> findAll();
}
