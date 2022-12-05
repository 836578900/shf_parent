package com.tong.shf.controller;

import com.tong.shf.entity.Permission;
import com.tong.shf.service.PermissionService;
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
 * @create 2022-12-05 19:28
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @DubboReference
    private PermissionService permissionService;

    @RequestMapping
    public String index(Map map){
        List<Permission> list = permissionService.findAll();
        map.put("list",list);
        return "permission/index";
    }

    @RequestMapping(value = "/create")
    public String create(Permission permission,Map map){
        map.put("permission",permission);
        return "permission/create";
    }

    @RequestMapping(value = "/save")
    public String save(Permission permission){
        permissionService.insert(permission);
        return "common/success";
    }

    @RequestMapping(value = "/edit/{permissionId}")
    public String edit(@PathVariable Long permissionId,Map map){
        Permission permission = permissionService.getById(permissionId);
        map.put("permission",permission);
        return "permission/edit";
    }

    @RequestMapping(value = "/update")
    public String update(Permission permission){
        permissionService.update(permission);
        return "common/success";
    }

    @RequestMapping(value = "/delete/{permissionId}")
    public String delete(@PathVariable Long permissionId){
        permissionService.delete(permissionId);
        return "redirect:/permission";
    }
}
