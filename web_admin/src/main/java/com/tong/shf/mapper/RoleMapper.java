package com.tong.shf.mapper;

import com.tong.shf.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-25 11:17
 */
public interface RoleMapper extends  BaseMapper<Role>{
    List<Role> findAll();
}
