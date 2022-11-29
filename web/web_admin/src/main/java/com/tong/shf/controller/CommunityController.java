package com.tong.shf.controller;


import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Community;
import com.tong.shf.entity.Dict;
import com.tong.shf.result.Result;
import com.tong.shf.service.CommunityService;
import com.tong.shf.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-29 16:17
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {
    @DubboReference
    private CommunityService communityService;
    @DubboReference
    private DictService dictService;

    @RequestMapping
    public String index(HttpServletRequest request, Map map){
        Map<String, Object> filters = getFilters(request);
        PageInfo<Community> page = communityService.findPage(filters);
        List<Dict> areaList = dictService.findListByDictCode("beijing");

        map.put("filters",filters);
        map.put("page",page);
        map.put("areaList",areaList);

        return "community/index";
    }

    @RequestMapping(value = "/create")
    public String create(Map map){
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList",areaList);
        return "community/create";
    }

    @RequestMapping(value = "/save")
    public String save(Community community){
        communityService.insert(community);
        return "common/success";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id,Map map){
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList",areaList);
        Community community = communityService.getById(id);
        map.put("community",community);
        return "community/edit";
    }

    @RequestMapping(value = "/update")
    public String update(Community community){
        communityService.update(community);
        return "common/success";
    }
    
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id){
        communityService.delete(id);
        return "redirect:/community";
    }

}
