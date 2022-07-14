package com.zeng.springcloud.controller;

import com.zeng.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
public class DeptConsumerController {

    //消费者不该有service层
    //RestTemplate注册到bean直接调用
    @Autowired
    private RestTemplate restTemplate;      //提供多种访问远程的http服务的方法，简单的restful服务末班

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @RequestMapping("/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add" ,dept ,Boolean.class);
    }

    @RequestMapping("/get/{id}")
    public Dept get(@PathVariable Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id  ,Dept.class);
    }
    @RequestMapping("/list")
    public List<Dept> list(){
        System.out.println("---------------");
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list" , List.class);
    }
}
