package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CourseMapper;
import com.example.dao.StudentMapper;
import com.example.model.CourseModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceDatabase implements CourseService
{
    @Autowired
    private StudentMapper student;


    @Override
    public CourseModel selectCourse (String idCourse)
    {
        log.info ("select course with ID {}", idCourse);
        return student.selectCourse (idCourse);
    }


    @Override
    public List<CourseModel> selectAllCourses ()
    {
        log.info ("select all courses");
        return student.selectAllCourses();
    }
}
