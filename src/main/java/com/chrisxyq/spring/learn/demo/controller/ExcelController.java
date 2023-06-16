package com.chrisxyq.spring.learn.demo.controller;

/**
 * @author chrisxu
 * @create 2022-03-16 12:11
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.chrisxyq.spring.learn.demo.entity.Student;
import com.chrisxyq.spring.learn.demo.listener.WebStudentListener;
import com.chrisxyq.spring.learn.demo.utils.DataGetter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("student")
public class ExcelController {
    @Autowired
    private WebStudentListener webStudentListener;

    @Lookup
    private WebStudentListener getWebStudentListener() {
        return null;
    }
    /**
     * apache poi读取上传文件
     *
     * @param uploadExcel
     * @return
     */
    @RequestMapping("read0")
    @ResponseBody
    public String readExcel0(MultipartFile uploadExcel) {
        try {
            InputStream inputStream = uploadExcel.getInputStream();
//            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            String stringCellValue = cell.getStringCellValue();
//            double numericCellValue = cell.getNumericCellValue();
            System.out.println(stringCellValue);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    /**
     * easyexcel读取上传文件
     *
     * @param uploadExcel
     * @return
     */
    @RequestMapping("read")
    @ResponseBody
    public String readExcel(MultipartFile uploadExcel) {
        try {
            ExcelReaderBuilder readWorkBook = EasyExcel.read(uploadExcel.getInputStream(), Student.class, webStudentListener);
            readWorkBook.sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * easyexcel读取上传文件
     * 读取上传文件行数
     *
     * @param uploadExcel
     * @return
     */
    @RequestMapping("read1")
    @ResponseBody
    public String readExcel1(MultipartFile uploadExcel) {
        try {
            ExcelReader excelReader = EasyExcelFactory.read(uploadExcel.getInputStream()).build().read(new ReadSheet(0));
            int row = excelReader.analysisContext().readRowHolder().getRowIndex();
            List<List<String>> head = excelReader.analysisContext().readSheetHolder().getHead();
            System.out.println(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * easyexcel读取上传文件
     * 读取文件表头
     *
     * @param uploadExcel
     * @return
     */
    @RequestMapping("read2")
    @ResponseBody
    public String readExcel2(MultipartFile uploadExcel) {
        try {
            ExcelReaderBuilder readWorkBook = EasyExcel.read(uploadExcel.getInputStream(), Student.class, webStudentListener);
            System.out.println(webStudentListener.getTblHeadMap());
            readWorkBook.sheet().doRead();
            System.out.println(webStudentListener.getTblHeadMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * easyexcel
     * 文件下载
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("write")
    @ResponseBody
    public String writeExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        sheet.doWrite(DataGetter.initData());
        return "success";
    }

    /**
     * easyexcel
     * 根据模板文件下载
     * 多条数据写入
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("writetemplate")
    @ResponseBody
    public String writeExcelTemplate(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("模板下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        String template = "template.xlsx";
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        List<Student> students = DataGetter.initData();
        sheet.doFill(students);
        return "success";
    }


    /**
     * easyexcel
     * 根据模板文件下载
     * 单条数据写入
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("writetemplate2")
    @ResponseBody
    public String writeExcelTemplate2(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("模板下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        String template = "singleTemplate.xlsx";
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        List<Student> students = DataGetter.initData();
        sheet.doFill(students.get(0));
        return "success";
    }

}
