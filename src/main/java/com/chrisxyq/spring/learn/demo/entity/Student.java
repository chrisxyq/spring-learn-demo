package com.chrisxyq.spring.learn.demo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(20)
//内容行高
//@ContentRowHeight()
//表头行高
//@HeadRowHeight
public class Student {

    //@ExcelProperty(value = "编号",index = 3)
    @ExcelIgnore
    private String id;
    @ExcelProperty(value = "学生姓名")
    //@ColumnWidth(30)
    private String name;
    @ExcelProperty(value = "学生性别")
    private String gender;

    @ExcelProperty(value = "学生出生日期")
    /**
     * 指定excel输出日期格式
     */
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(20)
    private Date birthday;
}