package com.morange.business.common.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 * @commit:
 * getList:    返回ResponsePage
 * getListEx:  返回ResponsePageEx
 * getListDto: 统一参数和返回结果，本系统将推荐使用这个方法
 */

import com.alibaba.fastjson.JSON;
import com.morange.business.common.entity.ComTypeCode;
import com.morange.business.common.entity.TypeCodeId;
import com.morange.business.common.service.ComTypeCodeService;
import com.morange.system.error.exception.GlobalException;
import com.morange.system.error.exception.GlobalExceptionUtils;
import com.morange.system.result.Result;
import com.morange.system.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("code")
public class ComTypeCodeController {

    private final static Logger logger = LoggerFactory.getLogger(ComTypeCodeController.class);

    @Autowired
    private ComTypeCodeService comTypeCodeService;

    @RequestMapping(path = "list")
    @ResponseBody
    public Result<ComTypeCode> getTypeCodes(@RequestBody(required = true) String type) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(type));
            List<ComTypeCode> comTypeCodes = comTypeCodeService.findByType(type);
            logger.debug("comTypeCodes list: "+JSON.toJSONString(comTypeCodes));
            return ResultUtils.success(comTypeCodes);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(type));
        }
    }

    @RequestMapping(path = "names")
    @ResponseBody
    public Result<Map> getNames(@RequestBody Map type) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(type));
            List<ComTypeCode> comTypeCodes = comTypeCodeService.findByType(type.get("type").toString());
            List<Map> names = new ArrayList<>();
            for(ComTypeCode item: comTypeCodes){
                Map<String, String> name = new HashMap<>();
                name.put("code", item.getId().getCode());
                name.put("name", item.getName());
                names.add(name);
            }
            logger.debug("comTypeCodes list: "+JSON.toJSONString(names));
            return ResultUtils.success(names);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(type));
        }
    }

    @RequestMapping(path = "saveAll", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveAll(@RequestBody List<ComTypeCode> comTypeCodes) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(comTypeCodes));
            if (ObjectUtils.isEmpty(comTypeCodes) &&(comTypeCodes.size() <= 0)) {
                GlobalExceptionUtils.throwException("not data");
            }

            int icount= comTypeCodeService.saveAll(comTypeCodes);
            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> save(@RequestBody TypeCodeId id) throws GlobalException {
        try {
            logger.debug("request param: name="+JSON.toJSONString(id));
            if (ObjectUtils.isEmpty(id)) {
                GlobalExceptionUtils.throwException("name or type is empty");
            }
            if (!comTypeCodeService.existsById(id)){
                return ResultUtils.success("type="+id.getType()+"; code="+id.getCode()+", 已经存在");
            }else{
                ComTypeCode comTypeCode = new ComTypeCode();
                comTypeCode.setId(id);
                comTypeCode.setName(id.getCode());
                comTypeCode.setDescription(id.getType());
                comTypeCode.setEnabled(true);
                comTypeCode.setCreatedBy("admin");
                comTypeCode.setCreatedDate(LocalDateTime.now());
                comTypeCode.setUpdatedBy("admin");
                comTypeCode.setUpdatedDate(LocalDateTime.now());
                ComTypeCode code = comTypeCodeService.save(comTypeCode);
                return ResultUtils.success(JSON.toJSONString(code));
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "deleteByType", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteByType(@RequestBody String type) throws GlobalException {
        try {
            logger.debug("request param: type="+type);
            if (ObjectUtils.isEmpty(type)) {
                GlobalExceptionUtils.throwException("not data");
            }
            comTypeCodeService.deleteByType(type);
            return ResultUtils.success("success delete"+type);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteById(@RequestBody TypeCodeId id) throws GlobalException {
        try {
            logger.debug("request param: type="+JSON.toJSONString(id));
            if (ObjectUtils.isEmpty(id)) {
                GlobalExceptionUtils.throwException("not data");
            }
            comTypeCodeService.deleteById(id);
            return ResultUtils.success("success delete");
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "existById", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> existById(@RequestBody TypeCodeId id) throws GlobalException {
        try {
            logger.debug("request param: type="+JSON.toJSONString(id));
            if (ObjectUtils.isEmpty(id)) {
                GlobalExceptionUtils.throwException("type and code is empty");
            };

            Boolean isExists = comTypeCodeService.existsById(id);
            return ResultUtils.success(isExists);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

}
