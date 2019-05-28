package com.morange.business.fit.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 * @commit:
 * getList:    返回ResponsePage
 * getListEx:  返回ResponsePageEx
 * getListDto: 统一参数和返回结果，本系统将推荐使用这个方法
 */

import com.alibaba.fastjson.JSON;
import com.morange.business.fit.controller.dto.FitStudentDto;
import com.morange.business.fit.entity.FitStudent;
import com.morange.business.fit.service.FitStudentService;
import com.morange.system.dto.BodyPageDto;
import com.morange.system.error.exception.GlobalException;
import com.morange.system.error.exception.GlobalExceptionUtils;
import com.morange.system.page.RequestPage;
import com.morange.system.page.ResponsePageUtils;
import com.morange.system.result.Result;
import com.morange.system.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("student")
public class FitStudentController {

    private final static Logger logger = LoggerFactory.getLogger(FitStudentController.class);

    @Autowired
    private FitStudentService fitStudentService;

    @RequestMapping(path = "listDto")
    @ResponseBody
    public Result<FitStudent> getListDto(@RequestBody(required = true) BodyPageDto<FitStudentDto, RequestPage> requestDto) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(requestDto));
            FitStudentDto fitStudentDto = null;
            if (ObjectUtils.isEmpty(requestDto.getBody())){
//                GlobalExceptionUtils.throwException(-1, "body is null", requestDto);
                fitStudentDto = new FitStudentDto();
            }else{
                fitStudentDto = requestDto.getBody();
            }


            // check param
            RequestPage page = null;
            if (ObjectUtils.isEmpty(requestDto.getPage())){
                page = new RequestPage();
            }else{
                page = requestDto.getPage();
            }

            if (page.isPaged()){
                Page<FitStudent> fitStudents = fitStudentService.findStudents(fitStudentDto.getCode(),
                        fitStudentDto.getName(), fitStudentDto.getGrade(), fitStudentDto.getSpecialty(),
                        page.getPage(), page.getSize());
                logger.debug("fitStudents list: "+JSON.toJSONString(fitStudents));
                return ResultUtils.success(ResponsePageUtils.of(fitStudents));
            }else {
                List<FitStudent> fitStudents = fitStudentService.findStudents(fitStudentDto.getCode(),
                        fitStudentDto.getName(), fitStudentDto.getGrade(), fitStudentDto.getSpecialty());
                logger.debug("fitStudents list: "+JSON.toJSONString(fitStudents));
                return ResultUtils.success(fitStudents);
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(requestDto));
        }
    }

    @RequestMapping(path = "uploadJson", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadJson(@RequestBody List<FitStudent> fitStudents) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(fitStudents));
            if (ObjectUtils.isEmpty(fitStudents)) {
                GlobalExceptionUtils.throwException("not data");
            }
            if (fitStudents.size() <= 0) {
                GlobalExceptionUtils.throwException("not data");
            }
            int icount= fitStudentService.SaveAll(fitStudents);
            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> delete(@RequestBody List<String> codes) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(codes));
            if (ObjectUtils.isEmpty(codes)) {
                GlobalExceptionUtils.throwException("not data");
            }
            if (codes.size() <= 0) {
                GlobalExceptionUtils.throwException("not data");
            }
            int icount= fitStudentService.deleteByCode(codes);

            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

}
