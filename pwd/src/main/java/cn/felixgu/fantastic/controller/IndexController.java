package cn.felixgu.fantastic.controller;

import cn.felixgu.fantastic.dao.UserDao;
import cn.felixgu.fantastic.dao.impl.UserDaoImpl;
import cn.felixgu.fantastic.entity.FanUserEntity;
import cn.felixgu.fantastic.entity.UserEntity;
import cn.felixgu.fantastic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.Util;
import javax.validation.ConstraintDeclarationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class IndexController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/", produces = "application/json")
    public String index(){

        FanUserEntity u = new FanUserEntity();

        u.setId("dfdc500e-5d84-4e66-a7c8-394e5706b8b0");

        u = userService.getUser(u);
        System.out.println(u.toString());

        u.setCreateDate(System.currentTimeMillis());

        return String.valueOf(userService.updateUser(u));
    }

    @RequestMapping(value = "/test", produces = "application/json")
    public Object test(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("a","alocal");
        return map;
    }
}
