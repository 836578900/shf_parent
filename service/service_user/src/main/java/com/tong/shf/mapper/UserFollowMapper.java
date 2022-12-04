package com.tong.shf.mapper;

import com.tong.shf.entity.UserFollow;
import com.tong.shf.vo.UserFollowVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 10:17
 */
public interface UserFollowMapper extends BaseMapper<UserFollow> {
    List<UserFollowVo> findUserFollowVoByUserId(long userId);
    UserFollow findFollowByUserAndHouse(@Param("userId") Long userId,@Param("houseId") Long houseId);
}
