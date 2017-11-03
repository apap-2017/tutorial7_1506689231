package com.example.service;

import java.util.List;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

public interface CourseService
{
    CourseModel selectCourse (String id);

    List<CourseModel> selectAllCourses ();

}