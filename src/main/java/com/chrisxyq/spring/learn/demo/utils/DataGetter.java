package com.chrisxyq.spring.learn.demo.utils;


import com.chrisxyq.spring.learn.demo.entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chrisxu
 * @create 2022-03-16 13:08
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class DataGetter {
    public static List<Student> initData() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("杭州黑马0" + i);
            student.setBirthday(new Date());
            student.setGender(i % 2 == 0 ? "男" : "女");
            students.add(student);
        }
        return students;
    }
}
