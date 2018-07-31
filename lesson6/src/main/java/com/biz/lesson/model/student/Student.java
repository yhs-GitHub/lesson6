package com.biz.lesson.model.student;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "student")
@Entity
public class Student {

    @Id
    @Column(length = 20)
    private String id;

    private Integer cmd;

    @Column(length = 20)
    private String name;

    private Date birthday;

    @Column(length = 20)
    private String gender;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", cmd='" + cmd + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                '}';
    }
}
