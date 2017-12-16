package com.example.redis.service;

import com.example.redis.dao.UserInfoEntity;
import com.example.redis.dao.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By jack on 16/12/2017
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfoEntity> findAll() {
        return userInfoRepository.findAll();
    }
}
