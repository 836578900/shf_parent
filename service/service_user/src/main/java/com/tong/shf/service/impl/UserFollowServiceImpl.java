package com.tong.shf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.UserFollow;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.UserFollowMapper;
import com.tong.shf.service.DictService;
import com.tong.shf.service.UserFollowService;
import com.tong.shf.vo.UserFollowVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 10:15
 */
@DubboService
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {
    @Autowired
    private UserFollowMapper userFollowMapper;
    @DubboReference
    private DictService dictService;

    @Override
    public BaseMapper<UserFollow> getEntityMapper() {
        return userFollowMapper;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, long userId) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserFollowVo> userFollowVoList = userFollowMapper.findUserFollowVoByUserId(userId);
        for (UserFollowVo userFollowVo : userFollowVoList) {
            userFollowVo.setHouseTypeName(dictService.getById(userFollowVo.getHouseTypeId()).getName());
            userFollowVo.setFloorName(dictService.getById(userFollowVo.getFloorId()).getName());
            userFollowVo.setDirectionName(dictService.getById(userFollowVo.getDirectionId()).getName());
        }
        return new PageInfo<>(userFollowVoList,3);
    }

    @Override
    public UserFollow findFollowByUserAndHouse(Long userId, Long houseId) {
        return userFollowMapper.findFollowByUserAndHouse(userId,houseId);
    }
}
