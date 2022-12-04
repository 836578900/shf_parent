package com.tong.shf.service;

import com.tong.shf.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 14:38
 */
public interface DictService{
    List<Map<String,Object>> findZnodes(long id);
    List<Dict> findListByDictCode(String dictCode);
    List<Dict> findListByParentId(long id);
    Dict getById(long id);
}
