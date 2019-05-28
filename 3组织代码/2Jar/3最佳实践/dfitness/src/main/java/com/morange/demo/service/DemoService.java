package com.morange.demo.service;

import com.morange.demo.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.morange.demo.repository.DemoRepository;

import java.util.Date;
import java.util.List;

@Service
public class DemoService {

    private DemoRepository demoRepository;

    @Autowired
    public void setDemoRepository(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Transactional(readOnly = true)
    public boolean validate(String username, String password) {
        List<Demo> demos = demoRepository.findByUsernameAndPassword(username, password);
        return demos.size() > 0;
    }

    @Transactional(readOnly = true)
    public Demo findUserByUserName(String username) {
        return demoRepository.findByUsername(username);
    }

    @Transactional(readOnly = false)
    public void update(String username, String password, String phone) {
        Demo demo = demoRepository.findByUsername(username);
        demo.setPassword(password);
        demo.setPhone(phone);
        demoRepository.save(demo);
    }

    @Transactional(readOnly = false)
    public Demo insert(String username, String password, String phone) {
        Demo demo = new Demo();
        demo.setUsername(username);
        demo.setPassword(password);
        demo.setPhone(phone);
        demo.setCreated(new Date());
        demo = demoRepository.save(demo);
        return demo;
    }

    @Transactional(readOnly = false)
    public void delete(String username) {
        Demo demo = demoRepository.findByUsername(username);
        if (demo != null) {
            demoRepository.delete(demo);
        }
    }

}