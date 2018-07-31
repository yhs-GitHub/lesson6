package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.student.StudentManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Student;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("school/student")
public class StudentController extends BaseController {

    @Autowired
    @Qualifier("studentDetailsManager")
    private StudentManager manager;

    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("school/student/add");
        modelAndView.addObject("cmd", "add");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        Student student = manager.getStudent(id);
        BusinessAsserts.exists(student, id);
        ModelAndView modelAndView = new ModelAndView("school/student/add");
        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
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
    public ModelAndView saveEdit(Student student,HttpServletRequest request) throws Exception {
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

    /*@RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    public void deleteStudent(String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
        manager.delStudent(id);
        request.getRequestDispatcher("/school/student/list.do").forward(request,response);
        //response.sendRedirect("/school/student/list.do");
    }*/

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list() throws Exception {
       ModelAndView modelAndView = new ModelAndView("school/student/list");
       List<Student> students = manager.listEnableStudents();
       modelAndView.addObject("students", students);
       return modelAndView;
    }
}
