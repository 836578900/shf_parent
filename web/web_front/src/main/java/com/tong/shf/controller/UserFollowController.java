package com.tong.shf.controller;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.UserFollow;
import com.tong.shf.entity.UserInfo;
import com.tong.shf.result.Result;
import com.tong.shf.service.UserFollowService;
import com.tong.shf.vo.UserFollowVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 10:08
 */
@Controller
@RequestMapping("userFollow")
@ResponseBody
public class UserFollowController {
    @DubboReference
    private UserFollowService userFollowService;

    @RequestMapping(value = "/auth/follow/{houseId}")
    public Result follow(@PathVariable long houseId, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        UserFollow userFollow = new UserFollow();
        userFollow.setHouseId(houseId);
        userFollow.setUserId(userInfo.getId());
        userFollowService.insert(userFollow);
        return Result.ok();
    }
    
    @RequestMapping(value = "/auth/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Integer pageNum,@PathVariable Integer pageSize,HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        PageInfo<UserFollowVo> page = userFollowService.findListPage(pageNum, pageSize, userInfo.getId());
        return Result.ok(page);
    }

    @RequestMapping(value = "/auth/cancelFollow/{userFollowId}")
    public Result cancelFollow(@PathVariable long userFollowId){
        userFollowService.delete(userFollowId);
        return Result.ok();
    }
}
