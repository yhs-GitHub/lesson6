package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.student.SubjectManager;
import com.biz.lesson.exception.BusinessAsserts;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("school/subject")
public class SubjectController extends BaseController {

    @Autowired
    @Qualifier("subjectDetailsManager")
    private SubjectManager manager;

    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("school/subject/add");
        modelAndView.addObject("cmd", "add");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id, HttpServletRequest request) throws Exception {
        Subject subject = manager.getSubject(id);
        BusinessAsserts.exists(subject, id);
        ModelAndView modelAndView = new ModelAndView("school/subject/add");
        modelAndView.addObject("subject", subject);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView saveAdd(Subject subject,HttpServletRequest request) throws Exception {
        System.out.println("----------------------------Add------------------------------");
        System.out.println(subject);
        manager.saveSubject(subject);
        return list();
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView saveEdit(Subject subject,HttpServletRequest request) throws Exception {
        System.out.println("----------------------------edit------------------------------");
        System.out.println(subject);
        manager.editSubject(subject);
        return list();
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Map<Object, Object> deleteStudent(Integer id, HttpServletRequest request) throws Exception {
        System.out.println("----------------------------delete------------------------------");
        System.out.println(id);
        Map<Object, Object> map = new HashMap<>();
        manager.delSubject(id);
        map.put("code",  200);
        return map;
    }

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list() throws Exception {
       ModelAndView modelAndView = new ModelAndView("school/subject/list");
       List<Subject> subjects = manager.listEnableSubject();
       modelAndView.addObject("subjects", subjects);
       return modelAndView;
    }
}
