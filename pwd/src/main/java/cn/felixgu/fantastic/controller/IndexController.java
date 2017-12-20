package cn.felixgu.fantastic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class IndexController {


    @RequestMapping(value = "/", produces = "application/json")
    public String index(){

        return Boolean.toString(true);
    }

    @RequestMapping(value = "/test", produces = "application/json")
    public Object test(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("a","alocal");
        return map;
    }
}
