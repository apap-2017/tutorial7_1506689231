package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

@Mapper
public interface CourseMapper
{   
    @Select("SELECT course.id_course, name, credits " +
    		"FROM studentcourse join course " +
    		"ON studentcourse.id_course = course.id_course")
    List<CourseModel> selectAllCourses ();
    
    //Method untuk memilih suatu mata kuliah berdasarkan id mata kuliah
    @Select("SELECT id_course, name, credits FROM course WHERE id_course = #{id_course}")
	CourseModel selectCourse (@Param("id_course") String id_course);
     
}
