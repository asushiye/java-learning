package com.morange.business.fit.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.alibaba.fastjson.JSON;
import com.morange.business.fit.controller.dto.FitItemCriterionDto;
import com.morange.business.fit.entity.FitItemCriterion;
import com.morange.business.fit.service.FitItemCriterionService;
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
@RequestMapping("item")
public class FitItemCriterionController {

    private final static Logger logger = LoggerFactory.getLogger(FitItemCriterionController.class);

    @Autowired
    private FitItemCriterionService fitItemCriterionService;

    @RequestMapping(path = "list")
    @ResponseBody
    public Result<FitItemCriterion> getListDto(@RequestBody(required = true) BodyPageDto<FitItemCriterionDto, RequestPage> requestDto) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(requestDto));
            FitItemCriterionDto fitItemCriterionDto = null;
            if (ObjectUtils.isEmpty(requestDto.getBody())){
                fitItemCriterionDto = new FitItemCriterionDto();
            }else{
                fitItemCriterionDto = requestDto.getBody();
            }

            // check param
            RequestPage page = null;
            if (ObjectUtils.isEmpty(requestDto.getPage())){
                page = new RequestPage();
            }else{
                page = requestDto.getPage();
            }

            if (page.isPaged()){
                Page<FitItemCriterion> fitItemCriterions = fitItemCriterionService.findItemCriterion(fitItemCriterionDto.getItem(),
                        fitItemCriterionDto.getCategory(), fitItemCriterionDto.getGender(), page.getPage(), page.getSize());
                logger.debug("fitItemCriterions list: "+JSON.toJSONString(fitItemCriterions));
                return ResultUtils.success(ResponsePageUtils.of(fitItemCriterions));
            }else {
                List<FitItemCriterion> fitItemCriterions = fitItemCriterionService.findItemCriterion(fitItemCriterionDto.getItem(),
                        fitItemCriterionDto.getCategory(), fitItemCriterionDto.getGender());
                logger.debug("fitItemCriterions list: "+JSON.toJSONString(fitItemCriterions));
                return ResultUtils.success(fitItemCriterions);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(requestDto));
        }
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> save(@RequestBody List<FitItemCriterion> fitItemCriterions) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(fitItemCriterions));
            if (ObjectUtils.isEmpty(fitItemCriterions)) {
                GlobalExceptionUtils.throwException("not data");
            }
            if (fitItemCriterions.size() <= 0) {
                GlobalExceptionUtils.throwException("not data");
            }
            int icount= fitItemCriterionService.SaveAll(fitItemCriterions);
            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> delete(@RequestBody List<Long> ids) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(ids));
            if (ObjectUtils.isEmpty(ids) && (ids.size() <= 0)) {
                GlobalExceptionUtils.throwException("request params not data");
            }
            int icount= fitItemCriterionService.deleteByIds(ids);
            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

}
