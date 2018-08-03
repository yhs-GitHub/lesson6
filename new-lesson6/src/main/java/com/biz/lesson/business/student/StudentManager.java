package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.student.Subject;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentManager extends BaseService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(StudentManager.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Student> listEnableStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) throws Exception{
        studentRepository.save(student);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findOne(username);
        if (student != null) {
            /*Hibernate.initialize(student .getRoles());
            user.setMenus(this.initUserMenu(user));*/
            logger.info("load student from db;");
        } else {
            throw new UsernameNotFoundException("Student:" + username + " is not exists");
        }
        return null;
    }

    public void delStudent(String id) {
        studentRepository.delete(id);
    }

    public Student getStudent(String id) {
        return studentRepository.findOne(id);
    }

    public void editStudent(Student student) {
        studentRepository.update(student.getId(),student.getGrade().getCode(),student.getName(),student.getBirthday(),student.getGender());
    }

    public List<Grade> listGrade() {
        return gradeRepository.findAll();
    }

    public List<Subject> listSubject() {
        return subjectRepository.findAll();
    }
}
