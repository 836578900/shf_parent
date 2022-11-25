package com.tong.shf.controller;

import com.tong.shf.entity.Role;
import com.tong.shf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-25 11:30
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String findAll(Map map) {
        //1. 调用业务层处理业务
        List<Role> list = roleService.findAll();
        //2. 将数据共享到请求域
        map.put("list", list);
        //3. 设置逻辑视图
        return "role/index";
    }
    
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
        
        return "role/create";
    }
    
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Role role){
        roleService.insert(role);
        return "common/success";
    }
}
