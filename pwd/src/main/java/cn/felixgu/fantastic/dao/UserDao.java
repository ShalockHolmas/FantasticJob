package cn.felixgu.fantastic.dao;

import cn.felixgu.fantastic.entity.FanUserEntity;

public interface UserDao {
    String saveUser(FanUserEntity user);
    void updateUser(FanUserEntity user);
    FanUserEntity getUser(FanUserEntity user);
}
