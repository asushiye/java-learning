package com.morange.business.fit.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.alibaba.fastjson.JSON;
import com.morange.business.fit.controller.dto.FitStudentItemScoreDto;
import com.morange.business.fit.entity.FitStudentItemScore;
import com.morange.business.fit.service.FitStudentItemScoreService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("score")
public class FitStudentItemScoreController {

    private final static Logger logger = LoggerFactory.getLogger(FitStudentItemScoreController.class);

    @Autowired
    private FitStudentItemScoreService fitStudentItemScoreService;

    @RequestMapping(path = "list")
    @ResponseBody
    public Result<FitStudentItemScore> getListDto(@RequestBody(required = true) BodyPageDto<FitStudentItemScoreDto, RequestPage> requestDto) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(requestDto));
            FitStudentItemScoreDto fitStudentItemScoreDto = null;
            if (ObjectUtils.isEmpty(requestDto.getBody())){
                fitStudentItemScoreDto = new FitStudentItemScoreDto();
            }else{
                fitStudentItemScoreDto = requestDto.getBody();
            }

            // check param
            RequestPage page = null;
            if (ObjectUtils.isEmpty(requestDto.getPage())){
                page = new RequestPage();
            }else{
                page = requestDto.getPage();
            }

            if (page.isPaged()){
                Page<FitStudentItemScore> fitStudentItemScores = fitStudentItemScoreService.findStudentItemScore(
                        fitStudentItemScoreDto.getTitle(),
                        fitStudentItemScoreDto.getCode(),
                        fitStudentItemScoreDto.getName(),
                        fitStudentItemScoreDto.getSpecialty(),
                        fitStudentItemScoreDto.getItem(),fitStudentItemScoreDto.getCategory(),
                        page.getPage(), page.getSize());
                logger.debug("fitStudentItemScores list: "+JSON.toJSONString(fitStudentItemScores));
                return ResultUtils.success(ResponsePageUtils.of(fitStudentItemScores));
            }else {
                List<FitStudentItemScore> fitStudentItemScores = fitStudentItemScoreService.findStudentItemScore(
                        fitStudentItemScoreDto.getTitle(),
                        fitStudentItemScoreDto.getCode(),
                        fitStudentItemScoreDto.getName(),
                        fitStudentItemScoreDto.getSpecialty(),
                        fitStudentItemScoreDto.getItem(), fitStudentItemScoreDto.getCategory());
                logger.debug("fitStudentItemScores list: "+JSON.toJSONString(fitStudentItemScores));
                return ResultUtils.success(fitStudentItemScores);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(requestDto));
        }
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> save(@RequestBody List<FitStudentItemScore> fitStudentItemScores) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(fitStudentItemScores));
            if (ObjectUtils.isEmpty(fitStudentItemScores) && (fitStudentItemScores.size() <= 0)) {
                GlobalExceptionUtils.throwException("request params not data");
            }

            int icount= fitStudentItemScoreService.SaveAll(fitStudentItemScores);
            if (icount >= 0) {
                return ResultUtils.success("学生考核项目保存成功",true);
            }else{
                return ResultUtils.success("",false);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> update(@RequestBody List<FitStudentItemScore> fitStudentItemScores) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(fitStudentItemScores));
            if (ObjectUtils.isEmpty(fitStudentItemScores) && (fitStudentItemScores.size() <= 0)) {
                GlobalExceptionUtils.throwException("request params not data");
            }

            int icount= fitStudentItemScoreService.updateAll(fitStudentItemScores);
            if (icount >= 0) {
                return ResultUtils.success("学生考核项目保存成功",true);
            }else{
                return ResultUtils.success("",false);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "deleteByTitle", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteByTitle(@RequestBody Map map) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(map));
            if (ObjectUtils.isEmpty(map)) {
                GlobalExceptionUtils.throwException("type is Empty");
            }
            fitStudentItemScoreService.deleteByTitle(map.get("title").toString());
            return ResultUtils.success("学生考核项目，删除成功");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),"");
        }
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> delete(@RequestBody List<Long> ids) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(ids));
            if (ObjectUtils.isEmpty(ids) && (ids.size() <= 0)) {
                GlobalExceptionUtils.throwException("not data");
            }
            int icount= fitStudentItemScoreService.deleteByIds(ids);

            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "StatisticsStudent", method = RequestMethod.POST)
    @ResponseBody
    public Result<List> StatisticsStudent(@RequestBody Map map) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(map));
            if (ObjectUtils.isEmpty(map)) {
                GlobalExceptionUtils.throwException("map not data");
            }
            List objs = fitStudentItemScoreService.getStatisticsStudent(map.get("title").toString());
            return ResultUtils.success(objs);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "StatisticsItem", method = RequestMethod.POST)
    @ResponseBody
    public Result<List> StatisticsItem(@RequestBody Map map) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(map));
            if (ObjectUtils.isEmpty(map)) {
                GlobalExceptionUtils.throwException("title not data");
            }
            List objs = fitStudentItemScoreService.getStatisticsItem(map.get("title").toString());

            return ResultUtils.success(objs);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "StatisticsItemCol", method = RequestMethod.POST)
    @ResponseBody
    public Result<List> StatisticsItemCol(@RequestBody Map map) throws GlobalException {
        try {
            logger.debug("request params: "+JSON.toJSONString(map));
            if (ObjectUtils.isEmpty(map)) {
                GlobalExceptionUtils.throwException("title not data");
            }
            List objs = fitStudentItemScoreService.getStatisticsItemCol(map.get("title").toString());
            return ResultUtils.success(objs);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

}
