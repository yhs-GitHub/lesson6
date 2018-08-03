package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Grade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface GradeRepository extends CommonJpaRepository<Grade,Integer> {
    @Modifying
    @Query("update Grade g set g.name=:name where g.code = :code")
    public void update(@Param("code")Integer code,@Param("name")String name);

}
