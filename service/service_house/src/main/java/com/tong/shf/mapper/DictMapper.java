package com.tong.shf.mapper;

import com.tong.shf.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 14:46
 */
public interface DictMapper {
    List<Dict> findZnodesByParentId(long id);
    Integer countParentId(long id);

    Dict findByDictCode(String dictCode);

    Dict getById(long id);
}
