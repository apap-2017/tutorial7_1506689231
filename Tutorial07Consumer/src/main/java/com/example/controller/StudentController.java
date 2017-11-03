package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;

    @RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("title", "Index");
        return "index";
    }
    
    @RequestMapping("/index")
    public String indexPath (Model model)
    {
    	model.addAttribute("title", "Index");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("title", "Add Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
    		Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);
        model.addAttribute("title", "Add Student");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        model.addAttribute("title", "View Student");
        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);
        model.addAttribute("title", "View Student");
        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title", "View All Students");
        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
    	model.addAttribute("title", "Delete Student");
    	if(student == null) { 
    		return "not-found";
    	}
    	
        studentDAO.deleteStudent (npm);
        return "delete";
    }
    
    /*
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
    	if(student == null) {
    		return "not-found";
    	}
    	
    	model.addAttribute("student", student);
        return "form-update";
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (
    		@RequestParam ( value = "npm" , required = false ) String npm ,
    		@RequestParam ( value = "name" , required = false ) String name,
    		@RequestParam ( value = "gpa" , required = false ) double gpa
    	)
    {
    	StudentModel updatedStudent = new StudentModel(npm, name, gpa, null);
    	studentDAO.updateStudent(updatedStudent);
    	
        return "success-update";
    }
    */
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
    	if(student == null) {
    		return "not-found";
    	}
    	model.addAttribute("title", "Update Student");
    	model.addAttribute("student", student);
        return "form-update-object-parameter";
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (Model model, StudentModel student)
    {
    	StudentModel updatedStudent = student;
    	studentDAO.updateStudent(updatedStudent);
    	model.addAttribute("title", "Update Student");
        return "success-update";
    }
    
    /*
    @RequestMapping("/course/view/{idCourse}")
    public String viewCourse (Model model,
            @PathVariable(value = "idCourse") String idCourse)
    {
        CourseModel course = studentDAO.selectCourse(idCourse);
        model.addAttribute("title", "View Course");
        if (course != null) {
            model.addAttribute ("course", course);
            return "view-course";
        } else {
            model.addAttribute ("id-course", idCourse);
            return "course-not-found";
        }
    }*/
}
