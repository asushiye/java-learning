package com.morange.demo.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 * @commit:
 * getList:    返回ResponsePage
 * getListEx:  返回ResponsePageEx
 * getListDto: 统一参数和返回结果，本系统将推荐使用这个方法
 */

import com.alibaba.fastjson.JSON;
import com.morange.demo.controller.dto.StudentDto;
import com.morange.demo.controller.dto.StudentDtoEx;
import com.morange.demo.entity.Student;
import com.morange.demo.service.StudentService;
import com.morange.demo.service.file.StudentExcelService;
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
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("api/demo/student")
public class StudentController {

    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentExcelService studentExcelService;

    @RequestMapping(path = "list")
    @ResponseBody
    public Result<Student> getList(@RequestBody StudentDtoEx studentDto) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(studentDto));
            if (studentDto.isPaged()){
                Page<Student> students = studentService.findStudents(studentDto.getCode(),
                        studentDto.getName(), studentDto.getGrade(), studentDto.getSpecialty(),
                        studentDto.getPage(), studentDto.getSize());
                logger.debug("request param: "+JSON.toJSONString(students));
                return ResultUtils.success(ResponsePageUtils.of(students));
            }else {
                List<Student> students = studentService.findStudents(studentDto.getCode(),
                        studentDto.getName(), studentDto.getGrade(), studentDto.getSpecialty());
                logger.debug("request param: "+JSON.toJSONString(students));
                return ResultUtils.success(students);
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(studentDto));
        }
    }

    @RequestMapping(path = "listEx")
    @ResponseBody
    public Result<Student> getListEx(@RequestBody StudentDtoEx studentDto) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(studentDto));
            if (studentDto.isPaged()){
                Page<Student> students = studentService.findStudents(studentDto.getCode(),
                        studentDto.getName(), studentDto.getGrade(), studentDto.getSpecialty(),
                        studentDto.getPage(), studentDto.getSize());
                System.out.println(JSON.toJSONString(students));
                return ResultUtils.success(ResponsePageUtils.ofEx(students));
            }else {
                List<Student> students = studentService.findStudents(studentDto.getCode(),
                        studentDto.getName(), studentDto.getGrade(), studentDto.getSpecialty());
                logger.debug("request param: "+JSON.toJSONString(students));
                return ResultUtils.success(students);
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(studentDto));
        }
    }

    @RequestMapping(path = "listDto")
    @ResponseBody
    public Result<Student> getListDto(@RequestBody(required = true) BodyPageDto<StudentDto, RequestPage> requestDto) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(requestDto));
            StudentDto studentDto = null;
            if (ObjectUtils.isEmpty(requestDto.getBody())){
                studentDto = new StudentDto();
            }else{
                studentDto = requestDto.getBody();
            }

            RequestPage page = null;
            if (ObjectUtils.isEmpty(requestDto.getPage())){
                page = new RequestPage();
            }else{
                page = requestDto.getPage();
            }

            if (page.isPaged()){
                Page<Student> fitStudents = studentService.findStudents(studentDto.getCode(),
                        studentDto.getName(), studentDto.getGrade(), studentDto.getSpecialty(),
                        page.getPage(), page.getSize());
                System.out.println(JSON.toJSONString(fitStudents));
                return ResultUtils.success(ResponsePageUtils.of(fitStudents));
            }else {
                List<Student> students = studentService.findStudents(studentDto.getCode(),
                        studentDto.getName(), studentDto.getGrade(), studentDto.getSpecialty());
                return ResultUtils.success(students);
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(), JSON.toJSONString(requestDto));
        }
    }


    @RequestMapping(path = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> save(@RequestBody List<Student> students) throws GlobalException {
        try {
            logger.debug("request param: "+JSON.toJSONString(students));
            if (ObjectUtils.isEmpty(students) && (students.size() <= 0)) {
                GlobalExceptionUtils.throwException("not data");
            }
            int icount= studentService.SaveAll(students);
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
            System.out.println(JSON.toJSONString(codes));
            if (ObjectUtils.isEmpty(codes)) {
                GlobalExceptionUtils.throwException("not data");
            }
            if (codes.size() <= 0) {
                GlobalExceptionUtils.throwException("not data");
            }
            int icount= studentService.deleteByCode(codes);

            return ResultUtils.success("success count: "+icount);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return ResultUtils.failure(ex.getMessage(),null);
        }
    }

    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public String uploadExcelFile(HttpSession session, @RequestParam("file") MultipartFile[] files, String note) throws GlobalException {
        try {
            String msg = "";
            if ((files.length >= 1) && (!files[0].isEmpty())) {
                String fileName = "upload/" + System.currentTimeMillis() + "_" + files[0].getOriginalFilename();
                System.out.println(fileName);
                files[0].transferTo(new File(fileName));
                System.out.println("-------------------------------------------------");
                System.out.println("getSize: " + files[0].getSize());
                System.out.println("getContentType: " + files[0].getContentType());
                System.out.println("getName: " + files[0].getName());
                System.out.println("getOriginalFilename：" + files[0].getOriginalFilename());
                System.out.println("note：" + note);
                System.out.println("-------------------------------------------------");
                studentExcelService.printExcel(fileName, msg);
            } else {
                throw new GlobalException("the file is empty");
            }
            return "redirect:success.html";
        } catch (IOException e) {
            throw new GlobalException("upload student error: " + e.getMessage());
        } catch (IllegalStateException e) {
            throw new GlobalException("upload student error: " + e.getMessage());
        } catch (Exception e) {
            throw new GlobalException("upload student error: " + e.getMessage());
        }
    }

    @RequestMapping(path = "downTmp", method = RequestMethod.GET)
    public String downTmp(ModelMap modelMap) {
        modelMap.put("students", null);
        modelMap.put("isTemplate", Boolean.TRUE);
        return "fitStudentExcelService";
    }

    @RequestMapping(path = "down", method = RequestMethod.GET)
    public String down(ModelMap modelMap, String code, String name, String grade, String specialty, int pageNum, int pageSize) {
        Page<Student> fitStudents = studentService.findStudents(code, name, grade, specialty, pageNum, pageSize);
        modelMap.put("students", fitStudents);
        modelMap.put("isTemplate", Boolean.FALSE);
        return "fitStudentExcelService";
    }
}
