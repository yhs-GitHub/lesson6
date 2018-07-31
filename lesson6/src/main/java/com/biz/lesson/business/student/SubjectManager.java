package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.model.student.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectManager extends BaseService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SubjectManager.class);

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> listEnableSubject() {
        return subjectRepository.findAll();
    }

    public void saveSubject(Subject subject) throws Exception{
        subjectRepository.save(subject);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       /* Student student = studentRepository.findOne(username);
        if (student != null) {
            Hibernate.initialize(user.getRoles());
            user.setMenus(this.initUserMenu(user));
            logger.info("load student from db;");
        } else {
            throw new UsernameNotFoundException("Student:" + username + " is not exists");
        }*/
        return null;
    }

    public void delSubject(Integer id) {
        subjectRepository.delete(id);
    }

    public Subject getSubject(Integer id) {
        return subjectRepository.findOne(id);
    }

    public void editSubject(Subject subject) {
        subjectRepository.update(subject.getId(),subject.getName());
    }
}
