package com.chrisxyq.spring.learn.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.chrisxyq.spring.learn.demo.entity.Student;
import com.chrisxyq.spring.learn.demo.listener.StudentListener;
import com.chrisxyq.spring.learn.demo.utils.DataGetter;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author chrisxu
 * @create 2022-03-16 11:13
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class ExcelTest {
    @Test
    public void test010() {
        String s="C:\\work\\3.txt";
        System.out.println(s);
        String[] split = s.split("\\\\");
        for (String s1 : new ArrayList<>(Arrays.asList(split))) {
            System.out.println(s1+" ");
        }
    }
    /**
     * 简单读
     * 工作簿：excel文件
     * 工作表：sheet
     */
    @Test
    public void test01() {
        //获得工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read("读取信息表.xlsx", Student.class, new StudentListener());
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
        sheet.doRead();
    }

    /**
     * 简单写
     * 工作簿：excel文件
     * 工作表：sheet
     */
    @Test
    public void test02() {
        //获得工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write("写入信息表.xlsx", Student.class);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        sheet.doWrite(DataGetter.initData());
    }
    @Test
    public void test03() {
        System.out.println(new DateTime().toString("yyyyMMdd"));
    }

}
