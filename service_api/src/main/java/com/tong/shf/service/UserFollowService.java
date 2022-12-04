package com.tong.shf.service;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.UserFollow;
import com.tong.shf.vo.UserFollowVo;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 10:14
 */
public interface UserFollowService extends BaseService<UserFollow> {

    PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, long userId);

    UserFollow findFollowByUserAndHouse(Long userId,Long houseId);
}
