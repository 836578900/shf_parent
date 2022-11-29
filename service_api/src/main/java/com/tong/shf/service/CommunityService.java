package com.tong.shf.service;

import com.tong.shf.entity.Community;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 16:19
 */
public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
