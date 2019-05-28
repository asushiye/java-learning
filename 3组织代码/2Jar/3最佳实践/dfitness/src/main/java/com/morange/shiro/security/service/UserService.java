package com.morange.shiro.security.service;

import com.morange.shiro.security.entity.User;
import com.morange.shiro.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.update(user.getUsername(), user.getEmail(), user.getPhone(), user.getId());
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            userRepository.deleteById(Integer.valueOf(id));
        }
    }

    @Transactional(readOnly = true)
    public Page<User> findByUsername(String username, Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        if (StringUtils.isEmpty(username)) {
            return userRepository.findAll(pageable);
        } else {
            return userRepository.findByUsername(username, pageable);
        }
    }

    @Transactional(readOnly = true)
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Transactional
    public void saveUserRole(int userId, String roleIdsStr) {
        userRepository.deleteUserRoleByUserId(userId);
        String[] ids=roleIdsStr.split(",");
        for (String id: ids){
            if (StringUtils.isEmpty(id)) continue;
            int roleId = Integer.valueOf(id);
            userRepository.InsertUserRoleByUserId(userId, roleId);
        }
    }

    @Transactional(readOnly = true)
    public User getById(int id) {
        return userRepository.getOne(id);
    }

}