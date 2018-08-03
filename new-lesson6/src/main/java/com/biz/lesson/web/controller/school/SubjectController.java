package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.student.SubjectManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Course;
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
import java.util.ArrayList;
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
    public ModelAndView edit(Integer cmd, HttpServletRequest request) throws Exception {
        Subject subject = manager.getSubject(cmd);
        BusinessAsserts.exists(subject, cmd);
        ModelAndView modelAndView = new ModelAndView("school/subject/add");
        modelAndView.addObject("subject", subject);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView saveAdd(Subject subject,HttpServletRequest request) throws Exception {
        manager.saveSubject(subject);
        return list();
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView saveEdit(Subject subject,HttpServletRequest request) throws Exception {
        manager.editSubject(subject);
        return list();
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Map<Object, Object> delete(Integer cmd, HttpServletRequest request) throws Exception {
        Map<Object, Object> map = new HashMap<>();
        manager.delSubject(cmd);
        map.put("code",  200);
        return map;
    }

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("school/subject/list");
        List<Subject> subjects = manager.listEnableSubject();
        List avg=new ArrayList();
        for (Subject subject:subjects){
            float total=0;
            int size=0;
            for (Course course:subject.getCourses()){
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
        modelAndView.addObject("subjects", subjects);
        modelAndView.addObject("avg",avg);
        return modelAndView;
    }
}
