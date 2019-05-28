package com.morange.demo.service;

import com.morange.demo.entity.Demo;
import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class DemoControllerServiceTest extends SpringBaseTest {

    private DemoService demoService;

    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @Test(groups = "demoService", priority = 0)
    public void deleteUser() {
        for (int i=0;i<10; i++){
            demoService.delete("zhenyun.su"+i);
        }
    }

    @Test(groups = "demoService", priority = 1)
    public void insertUser() {
        for (int i=0;i<10; i++){
            demoService.insert("zhenyun.su"+i, "111222", "13795467305");
        }
    }

    @Test(groups = "demoService", priority = 2)
    public void loginSuccess() {
        boolean isMatch = demoService.validate("zhenyun.su", "111222");
    }

    @Test(groups = "demoService", priority = 3)
    public void findUserByUserName() {
        Demo demo = demoService.findUserByUserName("zhenyun.su");
        if (demo != null) {
            System.out.println(demo.toString());
        }
    }

}