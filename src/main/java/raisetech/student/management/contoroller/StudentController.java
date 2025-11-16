package raisetech.student.management.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import raisetech.student.management.contoroller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.service.StudentService;


import java.util.List;


@Controller
public class StudentController {

    private StudentService service;
    private StudentConverter converter;


    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        List<Student> students = service.searchStudentList();
        List<StudentCourse> studentCourses = service.searchStudentCourseList();
        model.addAttribute("studentList", converter.convertStudentdetails(students, studentCourses));
        return "studentList";
    }


    @GetMapping("/studentcourseList")
    public List<StudentCourse> getStudentsCourseList(){
        return  service.searchStudentCourseList();
    }
}

