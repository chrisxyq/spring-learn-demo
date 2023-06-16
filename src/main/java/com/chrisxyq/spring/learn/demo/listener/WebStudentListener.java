package com.chrisxyq.spring.learn.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chrisxyq.spring.learn.demo.entity.Student;
import com.chrisxyq.spring.learn.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chrisxu
 * @create 2022-03-16 12:24
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
@Component
@Scope("prototype")
public class WebStudentListener extends AnalysisEventListener<Student> {
    @Autowired
    private StudentService studentService;
    List<Student> students = new ArrayList<>();
    private  Map tblHeadMap;

    public Map getTblHeadMap() {
        return tblHeadMap;
    }

    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        System.out.println("表头数据" + headMap);
        tblHeadMap=headMap;
    }

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("表数据" + student);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
