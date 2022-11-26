package com.tong.shf.mapper;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-26 14:18
 */
public interface BaseMapper<T> {
    void insert(T t);

    T getById(Serializable id);

    void update(T t);

    void delete(Serializable id);

    List<T> findPage(Map<String, Object> filters);
}
