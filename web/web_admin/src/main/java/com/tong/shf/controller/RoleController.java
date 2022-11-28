package com.tong.shf.controller;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Role;
import com.tong.shf.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
public class RoleController extends BaseController {
    @DubboReference
    private RoleService roleService;

    /*@RequestMapping
    public String findAll(Map map) {
        //1. 调用业务层处理业务
        List<Role> list = roleService.findAll();
        //2. 将数据共享到请求域
        map.put("list", list);
        //3. 设置逻辑视图
        return "role/index";
    }*/
    @RequestMapping
    public String findPage(Map map,HttpServletRequest request) {
        //1. 调用业务层处理业务
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);
        //2. 将数据共享到请求域
        map.put("page", page);
        map.put("filters",filters);
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

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,Map map){
        Role role = roleService.getById(id);
        map.put("role",role);
        return "role/edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Role role){
        roleService.update(role);
        return "common/success";
    }
    
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        roleService.delete(id);
        return "redirect:/role";
    }
}
