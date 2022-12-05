package com.tong.shf.controller;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.*;
import com.tong.shf.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 19:13
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    @DubboReference
    private HouseService houseService;
    @DubboReference
    private DictService dictService;
    @DubboReference
    private CommunityService communityService;
    @DubboReference
    private HouseImageService houseImageService;
    @DubboReference
    private HouseBrokerService houseBrokerService;
    @DubboReference
    private HouseUserService houseUserService;

    @RequestMapping
    public String index(Map map, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);
        map.put("filters",filters);
        map.put("page",page);
        getSource(map);
        return "house/index";
    }

    @RequestMapping(value = "/create")
    public String create(Map map){
        getSource(map);
        return "house/create";
    }

    @RequestMapping(value = "/save")
    public String save(House house){
        houseService.insert(house);
        return "common/success";
    }

    @RequestMapping(value = "/edit/{houseId}")
    public String edit(@PathVariable long houseId,Map map){
        House house = houseService.getById(houseId);
        map.put("house",house);
        getSource(map);
        return "house/edit";
    }

    @RequestMapping(value = "/update")
    public String update(House house){
        houseService.update(house);
        return "common/success";
    }

    @RequestMapping(value = "/delete/{houseId}")
    public String delete(@PathVariable long houseId){
        houseService.delete(houseId);
        return "redirect:/house";
    }

    @RequestMapping(value = "/publish/{houseId}/{status}")
    public String publish(@PathVariable long houseId,@PathVariable Integer status){
        houseService.publish(houseId,status);
        return "redirect:/house";
    }

    @RequestMapping(value = "/show/{houseId}")
    public String show(@PathVariable long houseId,Map map){
        //1、需要房源的详细信息
        House house = houseService.getById(houseId);
        //2、房源所在小区的详细信息，房源信息里有小区id
        Community community = communityService.getById(house.getCommunityId());
        //3、房源的房源图片信息
        List<HouseImage> houseImage1List = houseImageService.findListByHouseIdAndType(houseId, 1);
        //4、房源的房产图片信息
        List<HouseImage> houseImage2List = houseImageService.findListByHouseIdAndType(houseId, 2);
        //5、房源的经纪人信息
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(houseId);
        //6、房源的房东信息
        List<HouseUser> houseUserList = houseUserService.findListByHouseId(houseId);

        map.put("house",house);
        map.put("community",community);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);

        return "house/show";
    }


    protected void getSource(Map map){
        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");
        map.put("communityList",communityList);
        map.put("houseTypeList",houseTypeList);
        map.put("floorList",floorList);
        map.put("buildStructureList",buildStructureList);
        map.put("decorationList",decorationList);
        map.put("directionList",directionList);
        map.put("houseUseList",houseUseList);
    }

}
