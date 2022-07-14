package com.zeng.springcloud.controller;

import com.zeng.springcloud.pojo.Dept;
import com.zeng.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/add")
    public boolean addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/get/{id}")
    public Dept queryById(@PathVariable Long id){
        return deptService.queryById(id);
    }

    @GetMapping("/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    //获取注册进来的微服务消息
    @GetMapping("/discovery")
    public Object discovery(){
        //获取微服务列表
        List<String> services = client.getServices();
        System.out.println("discovery=>service:::" + services);

        //得到一个具体的微服务信息
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                    instance.getPort()+"\t"+
                    instance.getInstanceId()+"\t"+
                    instance.getScheme()+"\t"+
                    instance.getUri()+"\t"+
                    instance.getMetadata()
            );
        }
        return this.client;
    }
}
