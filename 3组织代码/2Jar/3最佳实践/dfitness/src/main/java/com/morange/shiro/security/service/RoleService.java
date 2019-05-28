package com.morange.shiro.security.service;

import com.morange.shiro.security.entity.Role;
import com.morange.shiro.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2018/9/17
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<Role> findByName(String name, Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        if (StringUtils.isEmpty(name)) {
            return roleRepository.findAll(pageable);
        } else {
            return roleRepository.findByName(name, pageable);
        }
    }

    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public Role getById(int id) {
        return roleRepository.getOne(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            roleRepository.deleteById(Integer.valueOf(id));
        }
    }

    @Transactional(readOnly = true)
    public List<Role> getRoleListByUserId(Integer id) {
        List<Role> Roles = roleRepository.findAll();
        List<Role> selectRoles = roleRepository.getRoleListByUserId(id);
        for (Role selectRole:selectRoles) {
            for (Role role:Roles) {
                if (selectRole.getId() == role.getId()){
                    role.setSelected(true);
                    break;
                }
            }
        }
        return Roles;
    }

}
