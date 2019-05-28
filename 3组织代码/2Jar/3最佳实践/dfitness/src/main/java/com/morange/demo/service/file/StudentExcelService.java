package com.morange.demo.service.file;

import com.morange.demo.entity.Student;
import com.morange.demo.service.StudentService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @since : 2019/1/2
 * 学号	姓名	性别	专业	年级
 * 3012016001	任爽	男	临床医学五年制	2017
 */

@Service("studentExcelService")
public class StudentExcelService extends AbstractXlsxView {
    @Autowired
    private StudentService studentService;

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=" + new String("学生导入模版.xlsx".getBytes(), "iso8859-1"));
        Boolean isTemplate = (Boolean) model.get("isTemplate");
        Sheet sheet = workbook.createSheet("学生信息");
        Row headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("学号");
        headRow.createCell(1).setCellValue("姓名");
        headRow.createCell(2).setCellValue("性别");
        headRow.createCell(3).setCellValue("专业");
        headRow.createCell(4).setCellValue("年级");
        if (isTemplate) {
            Row contentRow = sheet.createRow(1);
            contentRow.createCell(0).setCellValue("0123456789");
            contentRow.createCell(1).setCellValue("张三");
            contentRow.createCell(2).setCellValue("男");
            contentRow.createCell(3).setCellValue("临床医学五年制");
            contentRow.createCell(4).setCellValue("2017");
        } else {
            List<Student> fitStudents = (List<Student>) model.get("fitStudents");
            if (fitStudents != null) {
                int i = 1;
                for (Student fitStudent : fitStudents) {
                    Row contentRow = sheet.createRow(i++);
                    contentRow.createCell(0).setCellValue(fitStudent.getCode());
                    contentRow.createCell(1).setCellValue(fitStudent.getName());
                    contentRow.createCell(2).setCellValue(fitStudent.getGender());
                    contentRow.createCell(3).setCellValue(fitStudent.getSpecialty());
                    contentRow.createCell(4).setCellValue(fitStudent.getGrade());
                }
            }
        }
    }

    public Boolean printExcel(final String fullFile, String msg) {
        String suffix = fullFile.substring(fullFile.lastIndexOf(".") + 1);
        if ("xlsx".equals(suffix)) {
            return this.putOutXlsx(fullFile, msg);
        } else {
            System.out.println("Unknown file type: " + fullFile);
            return false;
        }
    }

    public static String getCellValue(final Cell cell) {
        if (cell != null) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }
        return "";
    }

    //验证第一行是否为学号	姓名	性别	专业	年级
    public static Boolean CheckHeader(final Row row, String msg) {
        if (row != null) {
            if (!"学号".equals(StudentExcelService.getCellValue(row.getCell(0)))) {
                msg = "第一列不是学号，请重新修改数据";
                return false;
            }

            if (!"姓名".equals(StudentExcelService.getCellValue(row.getCell(1)))) {
                msg = "第二列不是姓名，请重新修改数据";
                return false;
            }
            if (!"性别".equals(StudentExcelService.getCellValue(row.getCell(2)))) {
                msg = "第三列不是性别，请重新修改数据";
                return false;
            }
            if (!"专业".equals(StudentExcelService.getCellValue(row.getCell(3)))) {
                msg = "第四列不是专业，请重新修改数据";
                return false;
            }
            if (!"年级".equals(StudentExcelService.getCellValue(row.getCell(4)))) {
                msg = "第五列不是年级，请重新修改数据";
                return false;
            }
            return true;
        } else {
            msg = "不存在";
            return false;
        }
    }

    //验证表格内容是否为学号	姓名	性别	专业	年级的值
    public static Boolean CheckContent(final Row row, Student student, String msg) {
        if (row != null) {
            String cellValue = StudentExcelService.getCellValue(row.getCell(0));
            if (cellValue != "") {
                student.setCode(cellValue);
            } else {
                msg = "第1列，学号不能为空";
                return false;
            }

            cellValue = StudentExcelService.getCellValue(row.getCell(1));
            if (cellValue != "") {
                student.setName(cellValue);
            } else {
                msg = "第2列，姓名不能为空";
                return false;
            }

            cellValue = StudentExcelService.getCellValue(row.getCell(2));
            if (cellValue != "") {
                student.setGender(cellValue);
            } else {
                msg = "第3列，性别不能为空";
                return false;
            }

            cellValue = StudentExcelService.getCellValue(row.getCell(3));
            if (cellValue != "") {
                student.setSpecialty(cellValue);
            } else {
                msg = "第4列，专业不能为空";
                return false;
            }
            cellValue = StudentExcelService.getCellValue(row.getCell(4));
            if (cellValue != "") {
                student.setGrade(cellValue);
            } else {
                msg = "第5列，年级不能为空";
                return false;
            }
            return true;
        } else {
            msg = "不存在";
            return false;
        }
    }

    public Boolean putOutXlsx(final String fullFile, String msg) {
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(fullFile));

            if (StringUtils.isEmpty(wb)) {
                msg = "文件 [" + fullFile + "] 不能加载";
                return false;
            }
            Sheet sheet = wb.getSheetAt(0);

            if (StringUtils.isEmpty(sheet)) {
                msg = "文件 [" + fullFile + "] 不存在表格sheet";
                return false;
            }

            if (!StudentExcelService.CheckHeader(sheet.getRow(0), msg)) {
                msg = "第1行，" + msg;
                return false;
            }

            //遍历当前sheet中的所有行
            Row row = null;
            List<Student> fitStudents = new ArrayList<>();
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                //遍历所有的列
                Student fitStudent = new Student();
                if (StudentExcelService.CheckContent(row, fitStudent, msg)) {
                    fitStudents.add(fitStudent);
                } else {
                    msg = "第" + j + "行" + msg;
                    return false;
                }
            }

            if (fitStudents.size() >= 1) {
                studentService.SaveAll(fitStudents);
            }
            msg = "总共上传" + fitStudents.size() + "条数据";
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
