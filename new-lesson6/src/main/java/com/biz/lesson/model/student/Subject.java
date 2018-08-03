package com.biz.lesson.model.student;

import javax.persistence.*;
import java.util.List;

/**
 * 科目
 */
@Table(name = "subject")
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cmd;

    @Column(length = 20)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Course> courses;

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List <Course> getCourses() {
        return courses;
    }

    public void setCourses(List <Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "cmd=" + cmd +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
