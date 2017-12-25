package cn.felixgu.fantastic.service;

import cn.felixgu.fantastic.entity.FanUserEntity;

public interface UserService{
    String saveUser(FanUserEntity user);
    boolean updateUser(FanUserEntity user);
    FanUserEntity getUser(FanUserEntity user);
}


