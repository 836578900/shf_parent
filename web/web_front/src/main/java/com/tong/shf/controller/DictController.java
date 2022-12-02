package com.tong.shf.controller;

import com.tong.shf.entity.Dict;
import com.tong.shf.result.Result;
import com.tong.shf.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-02 19:24
 */
@Controller
@RequestMapping("/dict")
@ResponseBody
public class DictController {
    @DubboReference
    private DictService dictService;

    @RequestMapping(value = "/findListByDictCode/{code}")
    public Result findListByDictCode(@PathVariable String code){
        List<Dict> dictList = dictService.findListByDictCode(code);
        return Result.ok(dictList);
    }

    @RequestMapping(value = "/findListByParentId/{parentId}")
    public Result findListByParentId(@PathVariable long parentId){
        List<Dict> diceList = dictService.findListByParentId(parentId);
        return Result.ok(diceList);
    }
}
