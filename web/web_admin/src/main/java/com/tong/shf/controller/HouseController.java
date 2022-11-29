package com.tong.shf.controller;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Community;
import com.tong.shf.entity.Dict;
import com.tong.shf.entity.House;
import com.tong.shf.service.CommunityService;
import com.tong.shf.service.DictService;
import com.tong.shf.service.HouseService;
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
