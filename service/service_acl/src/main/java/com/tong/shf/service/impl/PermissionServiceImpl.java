package com.tong.shf.service.impl;

import com.tong.shf.entity.Permission;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.PermissionMapper;
import com.tong.shf.mapper.RolePermissionMapper;
import com.tong.shf.service.PermissionService;
import com.tong.shf.util.PermissionHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 17:18
 */
@DubboService
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public BaseMapper<Permission> getEntityMapper() {
        return permissionMapper;
    }


    @Override
    public List<Map<String, Object>> findZNodes(Long roleId) {
        List<Permission> permissionList = permissionMapper.findAll();
        List<Long> permissionIds = rolePermissionMapper.findPermissionIdsByRoleId(roleId);
        ArrayList<Map<String, Object>> zNodes = new ArrayList<>();

        for (Permission permission : permissionList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            if (permissionIds.contains(permission.getId())){
                map.put("checked",true);
            }
            zNodes.add(map);
        }
        return zNodes;
    }

    @Override
    public List<Permission> findByAdminId(Long adminId) {
        List<Permission> permissionList = permissionMapper.findByAdminId(adminId);
        List<Permission> permissionList1 = PermissionHelper.bulid(permissionList);
        return permissionList1;
    }

    @Override
    public List<Permission> findAll() {
        return PermissionHelper.bulid(permissionMapper.findAll());
    }

    @Override
    public void delete(Serializable id) {
        List<Permission> permissionList = permissionMapper.findPermissionByParentId(id);
        if (permissionList!=null&&permissionList.size()!=0){
            for (Permission permission : permissionList) {
                List<Permission> permissionByParentId = permissionMapper.findPermissionByParentId(permission.getId());
                if (permissionByParentId!=null&&permissionByParentId.size()!=0){
                    for (Permission permission1 : permissionByParentId) {
                        permissionMapper.delete(permission1.getId());
                    }
                }
                permissionMapper.delete(permission.getId());
            }
        }
        permissionMapper.delete(id);
    }
}
