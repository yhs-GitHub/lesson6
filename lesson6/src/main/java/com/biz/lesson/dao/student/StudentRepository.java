package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface StudentRepository extends CommonJpaRepository<Student,String> {

   @Modifying
   @Query("update Student s set s.name=:name,s.cmd=:cmd,s.birthday=:birthday,s.gender=:gender where s.id = :id")
   public void update(@Param("id")String id,@Param("cmd")Integer cmd,@Param("name")String name,@Param("birthday")Date birthday,@Param("gender")String gender);
}
