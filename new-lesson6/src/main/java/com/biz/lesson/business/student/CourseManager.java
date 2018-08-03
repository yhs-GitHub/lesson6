package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.CourseRepository;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.model.student.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CourseManager extends BaseService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CourseManager.class);

    @Autowired
    private CourseRepository courseRepository;

    /*public List<Grade> listEnableGrade() {
        return gradeRepository.findAll();
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Grade grade = gradeRepository.findOne(username);
        if (grade != null) {
            Hibernate.initialize(user.getRoles());
            user.setMenus(this.initUserMenu(user));
            logger.info("load student from db;");
        } else {
            throw new UsernameNotFoundException("Student:" + username + " is not exists");
        }*/
        logger.info("load grade from db;");
        return null;
    }
}
