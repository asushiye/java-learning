package com.morange.business.file.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 * @commit:
 * getList:    返回ResponsePage
 * getListEx:  返回ResponsePageEx
 * getListDto: 统一参数和返回结果，本系统将推荐使用这个方法
 */

import com.alibaba.fastjson.JSON;
import com.morange.system.result.Result;
import com.morange.system.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("file")
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true",maxAge = 3600)
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    private final static String SDIR="E:\\6_Java\\4_workdemo\\spring-angular-learning\\dfitness\\bfitness\\upload\\";

    @RequestMapping(path = "uploads", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadFiles(HttpSession session, @RequestParam("file") MultipartFile[] files) throws Exception {
        try {
            for (int i = 0; i < files.length; i++) {
                System.out.println("-------------------------------------------------");
                System.out.println("getSize: " + files[i].getSize());
                System.out.println("getContentType: " + files[i].getContentType());
                System.out.println("getName: " + files[i].getName());
                System.out.println("getOriginalFilename：" + files[i].getOriginalFilename());
                System.out.println("-------------------------------------------------");
                if (files[i].isEmpty()) {
                    continue;
                }
                String fileName = SDIR+System.currentTimeMillis() + "_" + files[i].getOriginalFilename();
                System.out.println(fileName);
                files[i].transferTo(new File(fileName));
            }
            return ResultUtils.success("");
        } catch (IOException e) {
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "I/O Exception");
        } catch (IllegalStateException e) {
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "IllegalStateException");
        }catch (Exception e) {
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "Exception");
        }
    }

    @RequestMapping(path = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadFile(HttpSession session, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            if (file.isEmpty()) {
                return ResultUtils.success("File is empty");
            }
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            System.out.println("-------------------------------------------------");
            System.out.println("getSize: " + file.getSize());
            System.out.println("getContentType: " + file.getContentType());
            System.out.println("getName: " + file.getName());
            System.out.println("getOriginalFilename：" + file.getOriginalFilename());
            System.out.println("-------------------------------------------------");
            String fileName = SDIR+System.currentTimeMillis() + "_" + file.getOriginalFilename();
            System.out.println(fileName);
            file.transferTo(new File(fileName));
            return ResultUtils.success("");
        } catch (IOException e) {
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "I/O Exception");
        } catch (IllegalStateException e) {
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "IllegalStateException");
        }catch (Exception e) {
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "Exception");
        }
    }


    @RequestMapping(value="add")
    @ResponseBody
    public Result<String> add(@RequestBody byte[] value, HttpServletResponse response){
        logger.debug("request param: "+JSON.toJSONString(""));
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(value);
            FileOutputStream fileOutputStream =  new FileOutputStream("temp.tmp");
            FileCopyUtils.copy(byteArrayInputStream, fileOutputStream);
            return ResultUtils.success("");
        }catch (Exception e){
            logger.debug(e.getMessage());
            return ResultUtils.failure(e.getMessage(), "");
        }
    }

}
