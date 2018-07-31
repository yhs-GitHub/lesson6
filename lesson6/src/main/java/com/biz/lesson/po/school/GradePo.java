package com.biz.lesson.po.school;

import com.biz.lesson.model.student.Grade;

public class GradePo{
    private Integer id;

    private String name;

    private Integer count;

    private float average;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public Grade toGrade(){
        Grade grade=new Grade();
        grade.setId(id);
        grade.setName(name);
        return grade;
    }
}
