package cn.felixgu.fantastic.service.impl;

import cn.felixgu.fantastic.dao.UserDao;
import cn.felixgu.fantastic.entity.FanUserEntity;
import cn.felixgu.fantastic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public String saveUser(FanUserEntity user) {
        return String.valueOf(userDao.saveUser(user));
    }

    @Override
    public boolean updateUser(FanUserEntity user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public FanUserEntity getUser(FanUserEntity user) {
        return userDao.getUser(user);
    }
}
