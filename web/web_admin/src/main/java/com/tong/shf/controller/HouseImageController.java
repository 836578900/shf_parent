package com.tong.shf.controller;

import com.tong.shf.entity.HouseImage;
import com.tong.shf.result.Result;
import com.tong.shf.service.HouseImageService;
import com.tong.shf.util.QiniuUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-01 19:25
 */
@Controller
@RequestMapping("/houseImage")
public class HouseImageController {
    @DubboReference
    private HouseImageService houseImageService;

    @RequestMapping(value = "/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable long houseId, @PathVariable Integer type, Map map){
        map.put("houseId",houseId);
        map.put("type",type);
        return "house/upload";
    }

    @RequestMapping(value = "/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(
            @PathVariable long houseId,
            @PathVariable Integer type,
            @RequestParam("file")MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            //将图片上传到七牛云
            String filename = UUID.randomUUID().toString();
            QiniuUtil.upload2Qiniu(file.getBytes(),filename);
            //将图片路径存储到数据库
            HouseImage houseImage = new HouseImage();
            houseImage.setHouseId(houseId);
            houseImage.setImageName(filename);
            houseImage.setType(type);
            houseImage.setImageUrl("http://rm5df0euk.hn-bkt.clouddn.com/"+filename);

            houseImageService.insert(houseImage);
        }

        return Result.ok();
    }

    @RequestMapping(value = "/delete/{houseId}/{id}")
    public String delete(@PathVariable long houseId,@PathVariable Integer id,Map map){
        HouseImage houseImage = houseImageService.getById(id);
        QiniuUtil.deleteFileFromQiniu(houseImage.getImageName());
        houseImageService.delete(id);
        return "redirect:/house/show/"+houseId;
    }

}
