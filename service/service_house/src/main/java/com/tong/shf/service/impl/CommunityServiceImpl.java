package com.tong.shf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Community;
import com.tong.shf.entity.Dict;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.CommunityMapper;
import com.tong.shf.mapper.DictMapper;
import com.tong.shf.service.CommunityService;
import com.tong.shf.util.CastUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 16:20
 */
@DubboService
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private DictMapper dictMapper;

    @Override
    public BaseMapper<Community> getEntityMapper() {
        return communityMapper;
    }

    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);
        PageHelper.startPage(pageNum, pageSize);
        List<Community> page = getEntityMapper().findPage(filters);

        for (Community community : page) {
            Long areaId = community.getAreaId();
            Long plateId = community.getPlateId();

            Dict areaDict = dictMapper.getById(areaId);
            Dict plateDict = dictMapper.getById(plateId);

            community.setAreaName(areaDict.getName());
            community.setPlateName(plateDict.getName());
        }
        return new PageInfo<Community>(page, 3);
    }

    @Override
    public List<Community> findAll() {
        return communityMapper.findAll();
    }

    @Override
    public Community getById(Serializable id) {
        Community community = communityMapper.getById(id);
        community.setAreaName(dictMapper.getById(community.getAreaId()).getName());
        community.setPlateName(dictMapper.getById(community.getPlateId()).getName());
        return community;
    }
}
