package com.biz.lesson.model.student;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Table(name = "student")
@Entity
public class Student {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 20)
    private String name;

    private Date birthday;

    @Column(length = 20)
    private String gender;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
    private List<Course> courses;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", courses=" + courses +
                ", grade=" + grade +
                '}';
    }
}
