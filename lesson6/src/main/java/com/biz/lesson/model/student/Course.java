package com.biz.lesson.model.student;

import javax.persistence.*;

/**
 * 选课
 */
@Table(name = "course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 20)
    private String stu_id;

    private Integer sub_id;

    private Integer mark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getSub_id() {
        return sub_id;
    }

    public void setSub_id(Integer sub_id) {
        this.sub_id = sub_id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
