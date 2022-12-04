package com.tong.shf.controller;

import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Admin;
import com.tong.shf.entity.AdminRole;
import com.tong.shf.entity.Role;
import com.tong.shf.service.AdminRoleService;
import com.tong.shf.service.AdminService;
import com.tong.shf.service.RoleService;
import com.tong.shf.util.QiniuUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-26 19:02
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @DubboReference
    private AdminService adminService;
    @DubboReference
    private RoleService roleService;
    @DubboReference
    private AdminRoleService adminRoleService;

    @RequestMapping
    public String findPage(HttpServletRequest request, Map map){
        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);
        map.put("filters",filters);//做数据回显
        map.put("page",page);//显示本页数据和分页的信息
        return "admin/index";
    }

    @RequestMapping("/create")
    public String create(){
        return "admin/create";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Admin admin){
        adminService.insert(admin);
        return "common/success";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,Map map){
        Admin admin = adminService.getById(id);
        map.put("admin",admin);
        return "admin/edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Admin admin){
        adminService.update(admin);
        return "common/success";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id){
        adminService.delete(id);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "/uploadShow/{adminId}")
    public String uploadShow(@PathVariable long adminId,Map map){
        map.put("adminId",adminId);
        return "admin/upload";
    }

    @RequestMapping(value = "/upload")
    public String upload(long adminId, @RequestParam("file")MultipartFile file) throws IOException {
        String filename = UUID.randomUUID().toString();
        QiniuUtil.upload2Qiniu(file.getBytes(),filename);
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setHeadUrl("http://rm5df0euk.hn-bkt.clouddn.com/"+filename);
        adminService.update(admin);
        return "common/success";
    }

    @RequestMapping(value = "/assignShow/{adminId}")
    public String assignShow(@PathVariable Long adminId,Map map){
        Map<String, List<Role>> roleByAdminId = roleService.findRoleByAdminId(adminId);
        map.putAll(roleByAdminId);
        map.put("adminId",adminId);
        return "admin/assignShow";
    }
    
    @RequestMapping(value = "/assignRole")
    public String assignRole(long adminId,Long[] roleIds){
        //roleIds是一个前台传过来的字符串，格式是[id,id,id,]，springmvc会自动给转化为一个long型数组，但是注意最后一个值为null值

        adminRoleService.insertAdminRole(adminId,roleIds);
        return "common/success";
    }

}
