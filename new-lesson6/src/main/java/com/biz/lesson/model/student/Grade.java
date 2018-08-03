package com.biz.lesson.model.student;

import javax.persistence.*;
import java.util.List;

/**
 * 课程
 */
@Table(name = "grade")
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer code;

    @Column(length = 20)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "grade")
    private List<Student> students;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
