package cn.felixgu.fantasticcustomer.controller;

import cn.felixgu.fantastic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/customer", produces = "application/json")
    public String index(){

        cn.felixgu.fantastic.entity.FanUserEntity u = new cn.felixgu.fantastic.entity.FanUserEntity();

        u.setId("dfdc500e-5d84-4e66-a7c8-394e5706b8b0");

        u = userService.getUser(u);
        System.out.println(u.toString());

        u.setCreateDate(System.currentTimeMillis());

        return String.valueOf(userService.updateUser(u));
    }

}
