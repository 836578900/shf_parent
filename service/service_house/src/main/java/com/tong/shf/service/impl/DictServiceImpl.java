package com.tong.shf.service.impl;

import com.tong.shf.entity.Dict;
import com.tong.shf.mapper.DictMapper;
import com.tong.shf.service.DictService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

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
 * @create 2022-11-29 14:48
 */
@DubboService
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictMapper;


    @Override
    public List<Map<String, Object>> findZnodes(long id) {
        List<Dict> znodes = dictMapper.findZnodesByParentId(id);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Dict znode : znodes) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",znode.getId());
            map.put("name",znode.getName());
            map.put("isParent",dictMapper.countParentId(znode.getId())>0);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        //根据dictCode找parentId
        Dict dict = dictMapper.findByDictCode(dictCode);
        //根据id查所有的子节点
        List<Dict> list = dictMapper.findZnodesByParentId(dict.getId());
        return list;
    }

    @Override
    public List<Dict> findListByParentId(long id) {
        return dictMapper.findZnodesByParentId(id);
    }
}
