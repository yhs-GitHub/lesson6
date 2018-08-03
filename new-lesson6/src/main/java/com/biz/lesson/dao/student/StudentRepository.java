package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Grade;
import com.biz.lesson.model.student.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface StudentRepository extends CommonJpaRepository<Student,String> {

   /*@Query("update Student s set s.name=:name,s.grade=:code,s.birthday=:birthday,s.gender=:gender where s.id = :id")*/
   @Modifying
   @Query(nativeQuery = true,value = "update student s set s.name=:name,s.grade_code=:code,s.birthday=:birthday,s.gender=:gender where s.id = :id")
   public void update(@Param("id")String id, @Param("code")Integer code, @Param("name")String name, @Param("birthday")Date birthday, @Param("gender")String gender);
}
