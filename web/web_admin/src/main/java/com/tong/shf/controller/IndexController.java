package com.tong.shf.controller;

import com.tong.shf.entity.Admin;
import com.tong.shf.entity.Permission;
import com.tong.shf.service.AdminService;
import com.tong.shf.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 18:10
 */
@Controller
public class IndexController {
    @DubboReference
    private AdminService adminService;
    @DubboReference
    private PermissionService permissionService;

    @RequestMapping("/")
    public String index(Map map){
        //模拟一个adminId
        Long adminId = 1L;
        Admin admin = adminService.getById(adminId);
        List<Permission> permissionList = permissionService.findByAdminId(adminId);
        map.put("admin",admin);
        map.put("permissionList",permissionList);
        return "frame/index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "frame/login";
    }
}
