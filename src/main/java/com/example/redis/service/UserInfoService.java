package com.example.redis.service;

import com.example.redis.dao.UserInfoEntity;

import java.util.List;

/**
 * Created By jack on 16/12/2017
 **/
public interface UserInfoService {
    public List<UserInfoEntity> findAll();
}
