package com.tong.shf.controller;

import com.tong.shf.entity.Admin;
import com.tong.shf.entity.HouseBroker;
import com.tong.shf.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 17:00
 */
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController {
    @DubboReference
    private HouseBrokerService houseBrokerService;

    @RequestMapping(value = "/create/{houseId}")
    public String create(@PathVariable long houseId, Map map){
        map.put("houseId",houseId);
        List<Admin> adminList = houseBrokerService.findHouseOtherBroker(houseId);
        map.put("adminList",adminList);
        return "houseBroker/create";
    }

    @RequestMapping(value = "/save")
    public String save(HouseBroker houseBroker){
        houseBrokerService.insert(houseBroker);
        return "common/success";
    }

    @RequestMapping(value = "/delete/{houseId}/{id}")
    public String delete(@PathVariable long houseId,@PathVariable Integer id){
        houseBrokerService.delete(id);
        return "redirect:/house/show/"+houseId;
    }
}
