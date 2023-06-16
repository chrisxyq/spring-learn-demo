package com.chrisxyq.spring.learn.demo.listener;

/**
 * @author chrisxu
 * @create 2022-03-16 11:16
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chrisxyq.spring.learn.demo.entity.Student;

/**
 * 文档读取监听器
 */
public class StudentListener extends AnalysisEventListener<Student> {
    /**
     * 每读取一行内容都会调用一次invoke
     *
     * @param student
     * @param analysisContext
     */
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("student:" + student);
    }

    /**
     * 读取完整个文档之后调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
