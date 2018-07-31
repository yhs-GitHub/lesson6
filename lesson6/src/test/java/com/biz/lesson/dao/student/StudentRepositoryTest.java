package com.biz.lesson.dao.student;

import com.biz.lesson.model.student.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.List;

public class StudentRepositoryTest {
    private ApplicationContext ctx = null;
    private StudentRepository studentRepository = null;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("application-content.xml");
        studentRepository = ctx.getBean(StudentRepository.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        ctx = null;
        System.out.println("tearDown");
    }

    @Test
    public void testFind() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students);
    }

    @Test
    public void testSave() {
        Student student=null;
        for(int i=0;i<20;i++){
            student=new Student();
            student.setId("123331"+i);
            student.setName("张三"+(i+1)+"号");
            Date date=new Date(1);
            date.setYear(93+i%5);
            date.setMonth(i%12+1);
            date.setDate((3*i)%28);
            student.setBirthday(date);
            if(i%2==1){
                student.setGender("男");
            }else{
                student.setGender("女");
            }
            student.setCmd(i%5+1);
            studentRepository.save(student);
        }
    }
    @Test
    public void testdel() {
        for(int i=0;i<20;i++){
            studentRepository.delete("123331"+i);
        }
    }

}
