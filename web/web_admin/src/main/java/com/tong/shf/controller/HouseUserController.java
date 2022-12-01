package com.tong.shf.controller;

import com.tong.shf.entity.HouseUser;
import com.tong.shf.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 18:03
 */
@Controller
@RequestMapping("/houseUser")
public class HouseUserController {
    @DubboReference
    private HouseUserService houseUserService;


    @RequestMapping(value = "/create/{houseId}")
    public String create(@PathVariable long houseId, Map map){
        map.put("houseId",houseId);
        return "houseUser/create";
    }

    @RequestMapping(value = "/save")
    public String save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return "common/success";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id,Map map){
        HouseUser houseUser = houseUserService.getById(id);
        map.put("houseUser",houseUser);
        return "houseUser/edit";
    }

    @RequestMapping(value = "/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return "common/success";
    }

    @RequestMapping(value = "/delete/{houseId}/{id}")
    public String delete(@PathVariable Integer id,@PathVariable long houseId){
        houseUserService.delete(id);
        return "redirect:/house/show/"+houseId;
    }


}
