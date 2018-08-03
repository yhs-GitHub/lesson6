package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.student.StudentManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Course;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import com.biz.lesson.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("school/student")
public class StudentController extends BaseController {

    @Autowired
    @Qualifier("studentDetailsManager")
    private StudentManager manager;

    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    private ModelAndView listGrade(){
        ModelAndView modelAndView = new ModelAndView("school/student/add");
        List<Grade> grades = manager.listGrade();
        modelAndView.addObject("grades", grades);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = listGrade();
        modelAndView.addObject("cmd", "add");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        Student student = manager.getStudent(id);
        BusinessAsserts.exists(student, id);
        ModelAndView modelAndView =listGrade();
        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/choose")
    public ModelAndView choose(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("school/student/reset");
        List<Subject> unchoose = manager.listSubject();
        System.out.println(unchoose);
        Student student = manager.getStudent(id);
        List<Course> courses = student.getCourses();
        List<Subject> choose=new ArrayList();
        Iterator<Subject> iterator = unchoose.iterator();
        while (iterator.hasNext()) {
            Subject subject=iterator.next();
            for(Course course:courses){
                if (subject.getCmd()==course.getSubject().getCmd()){
                    iterator.remove();
                    choose.add(subject);
                    break;
                }
            }
        }
        modelAndView.addObject("unchoose",unchoose);
        modelAndView.addObject("choose",choose);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView saveAdd(Student student,HttpServletRequest request) throws Exception {
        manager.saveStudent(student);
        return list();
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView saveEdit(Student student,Integer code,HttpServletRequest request) throws Exception {
        Grade grade=new Grade();
        grade.setCode(code);
        student.setGrade(grade);
        manager.editStudent(student);
        return list();
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Map<Object, Object> deleteStudent(String id, HttpServletRequest request) throws Exception {
        Map<Object, Object> map = new HashMap<>();
        manager.delStudent(id);
        map.put("code",  200);
        return map;
    }
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list() throws Exception {
       ModelAndView modelAndView = new ModelAndView("school/student/list");
       List<Student> students = manager.listEnableStudents();
       List avg=new ArrayList();
       for (Student student:students){
           float total=0;
           int size=0;
           for (Course course:student.getCourses()){
               if(course.getMark()!=null){
                   total+=course.getMark();
                   size+=1;
               }
           }
           if(size>0){
               avg.add(total/size);
           }else {
               avg.add(null);
           }
       }
       modelAndView.addObject("students", students);
       modelAndView.addObject("avg",avg);
       return modelAndView;
    }
}
