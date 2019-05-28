package com.morange.shiro.security.service;

import com.morange.shiro.security.entity.Operation;
import com.morange.shiro.security.repository.OperationRepository;
import com.morange.system.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2018/9/17
 */

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getOperationsByIds(String ids){
        Iterable<Integer> IdList =  GlobalUtils.asIntegerList(ids,",");
        if (IdList !=null){
            return operationRepository.findAllById(IdList);
        }else{
            return null;
        }
    }

    public List<Operation> getAll(){
        return operationRepository.findAll();
    }
}
