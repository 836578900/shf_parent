package com.tong.shf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.service.BaseService;
import com.tong.shf.util.CastUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-26 16:29
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseMapper<T> getEntityMapper();

    @Override
    public void insert(T t) {
        getEntityMapper().insert(t);
    }

    @Override
    public T getById(Serializable id) {
        return getEntityMapper().getById(id);
    }

    @Override
    public void update(T t) {
        getEntityMapper().update(t);
    }

    @Override
    public void delete(Serializable id) {
        getEntityMapper().delete(id);
    }

    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);
        PageHelper.startPage(pageNum, pageSize);
        List<T> page = getEntityMapper().findPage(filters);
        return new PageInfo<T>(page, 3);
    }
}
