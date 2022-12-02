package com.tong.shf.controller;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Community;
import com.tong.shf.entity.House;
import com.tong.shf.entity.HouseBroker;
import com.tong.shf.entity.HouseImage;
import com.tong.shf.result.Result;
import com.tong.shf.service.CommunityService;
import com.tong.shf.service.HouseBrokerService;
import com.tong.shf.service.HouseImageService;
import com.tong.shf.service.HouseService;
import com.tong.shf.vo.HouseQueryVo;
import com.tong.shf.vo.HouseVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-02 19:21
 */
@Controller
@RequestMapping("/house")
@ResponseBody
public class HouseController {
    @DubboReference
    private HouseService houseService;
    @DubboReference
    private CommunityService communityService;
    @DubboReference
    private HouseBrokerService houseBrokerService;
    @DubboReference
    private HouseImageService houseImageService;

    @RequestMapping(value = "/list/{pageNum}/{pageSize}")
    public Result findListPage(
            @PathVariable int pageNum,
            @PathVariable int pageSize,
            @RequestBody HouseQueryVo houseQueryVo){
        PageInfo<HouseVo> page = houseService.findListPage(pageNum, pageSize, houseQueryVo);
        return Result.ok(page);
    }
    
    @RequestMapping(value = "/info/{houseId}")
    public Result info(@PathVariable long houseId){
        House house = houseService.getById(houseId);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(houseId);
        List<HouseImage> houseImage1List = houseImageService.findListByHouseIdAndType(houseId, 1);
        Boolean isFollow = false;//用户是否关注房源，目前先写死，等登陆讲完后，在去判断
        Map<String,Object> map=new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        map.put("isFollow",isFollow);
        return Result.ok(map);
    }
}
