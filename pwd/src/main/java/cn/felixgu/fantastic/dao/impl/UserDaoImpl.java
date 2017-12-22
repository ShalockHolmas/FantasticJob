package cn.felixgu.fantastic.dao.impl;

import cn.felixgu.fantastic.dao.UserDao;
import cn.felixgu.fantastic.entity.FanUserEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends HibernateTemplate implements UserDao{

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }


    @Override
    public String saveUser(FanUserEntity user) {
        return (String) save(user);
    }

    @Override
    public void updateUser(FanUserEntity user) {
        update(user);
    }

    @Override
    public FanUserEntity getUser(FanUserEntity user) {
        return get(FanUserEntity.class, user.getId());
    }
}
