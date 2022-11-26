package com.tong.shf.service;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-26 14:23
 */
public interface BaseService<T> {
    void insert(T t);

    T getById(Serializable id);

    void update(T t);

    void delete(Serializable id);

    PageInfo<T> findPage(Map<String, Object> filters);
}
