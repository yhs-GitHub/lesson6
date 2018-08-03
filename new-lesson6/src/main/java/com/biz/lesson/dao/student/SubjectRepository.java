package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.student.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CommonJpaRepository<Subject,Integer> {
    @Modifying
    @Query("update Subject s set s.name=:name where s.cmd = :cmd")
    public void update(@Param("cmd")Integer cmd, @Param("name")String name);
}
