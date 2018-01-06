package com.example.redis.controller;

import com.example.redis.dao.UserInfoEntity;
import com.example.redis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("hello")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("users")
    @ResponseBody
    @Cacheable(value = "user",cacheManager = "redisCacheManagerString")
    public List<UserInfoEntity> findAll(){
        System.out.println("user........");
        return userInfoService.findAll();
    }

}
