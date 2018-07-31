package com.biz.lesson.web.controller.school;

import com.biz.lesson.business.student.GradeManager;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.student.Grade;
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
@RequestMapping("school/grade")
public class GradeController extends BaseController {

    @Autowired
    @Qualifier("gradeDetailsManager")
    private GradeManager manager;

    @Autowired
    @Qualifier("passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("school/grade/add");
        modelAndView.addObject("cmd", "add");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Integer id, HttpServletRequest request) throws Exception {
        Grade grade = manager.getGrade(id);
        BusinessAsserts.exists(grade, id);
        ModelAndView modelAndView = new ModelAndView("school/grade/add");
        modelAndView.addObject("grade", grade);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_USER_ADD')")
    public ModelAndView saveAdd(Grade grade,HttpServletRequest request) throws Exception {
        manager.saveGrade(grade);
        return list();
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_USER_EDIT')")
    public ModelAndView saveEdit(Grade grade,HttpServletRequest request) throws Exception {
        manager.editGrade(grade);
        return list();
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_USER_DELETE')")
    @ResponseBody
    public Map<Object, Object> deleteGrade(Integer id, HttpServletRequest request) throws Exception {
        Map<Object, Object> map = new HashMap<>();
        manager.delGrade(id);
        map.put("code",  200);
        return map;
    }

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_USER_LIST')")
    public ModelAndView list() throws Exception {
       ModelAndView modelAndView = new ModelAndView("school/grade/list");
       List<Grade> grades = manager.listEnableGrade();
       modelAndView.addObject("grades", grades);
       return modelAndView;
    }
}
