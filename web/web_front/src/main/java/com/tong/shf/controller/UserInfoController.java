package com.tong.shf.controller;

import com.tong.shf.entity.UserInfo;
import com.tong.shf.result.Result;
import com.tong.shf.result.ResultCodeEnum;
import com.tong.shf.service.UserInfoService;
import com.tong.shf.util.MD5;
import com.tong.shf.vo.LoginVo;
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

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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

    @RequestMapping(value = "/login")
    public Result login(@RequestBody LoginVo loginVo,HttpSession session){
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        //判断是否为空
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
            return Result.build(null,ResultCodeEnum.PARAM_ERROR);
        }
        //判断用户是否处于登陆状态
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        //判断用户是否存在
        if(userInfo == null){
            userInfo = userInfoService.findUserInfoByPhone(phone);
            if (userInfo == null) {
                return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
            }
        }
        //判断密码是否正确
        if (!userInfo.getPassword().equals(MD5.encrypt(password))){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        //判断用户是否处于锁定状态
        if (userInfo.getStatus()==0){
            return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //将登陆人信息放到会话域
        session.setAttribute("userInfo",userInfo);
        //将昵称响应给首页用于展示
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("nickName",userInfo.getNickName());
        return Result.ok(map);
    }

    @RequestMapping(value = "/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("userInfo");
        return Result.ok();
    }
}
