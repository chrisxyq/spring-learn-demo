package com.chrisxyq.spring.learn.demo.service;

import com.chrisxyq.spring.learn.demo.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chrisxu
 * @create 2022-03-16 12:12
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
@Component
public class StudentService {
    public void readExcel(List<Student> students) {
        for (Student student : students) {
            System.out.println("student:" + student);
        }
        System.out.println("===========");
    }
}
