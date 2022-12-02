package com.tong.shf.controller;

import com.tong.shf.entity.UserInfo;
import com.tong.shf.result.Result;
import com.tong.shf.result.ResultCodeEnum;
import com.tong.shf.service.UserInfoService;
import com.tong.shf.util.MD5;
import com.tong.shf.vo.RegisterVo;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-02 20:43
 */
@Controller
@RequestMapping("/userInfo")
@ResponseBody
public class UserInfoController {
    @DubboReference
    private UserInfoService userInfoService;

    
    @RequestMapping(value = "/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone , HttpSession session){
        String code = "8888";//模拟的验证码，真实环境应该是随机数
        session.setAttribute("code",code);
        session.setAttribute("phone",phone);
        return Result.ok(code);
    }
    
    @RequestMapping(value = "/register")
    public Result register(HttpSession session, @RequestBody RegisterVo registerVo){
        String code = registerVo.getCode();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String nickName = registerVo.getNickName();
        //判断数据非空
        if (StringUtils.isEmpty(code)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickName)){
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //判断验证码是否正确
        String trueCode = (String) session.getAttribute("code");
        if (!trueCode.equals(code)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //判断手机号是否重复
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if (userInfo!=null){
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //将数据保存到数据库
        userInfo=new UserInfo();
        userInfo.setNickName(nickName);
        userInfo.setPassword(MD5.encrypt(password));
        userInfo.setPhone(phone);
        userInfoService.insert(userInfo);
        return Result.ok();
    }
}
