package raisetech.student.management.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;


import java.util.Arrays;
import java.util.List;


@RestController
public class StudentController {

    private StudentService service;
    private StudentConverter converter;


    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentCourse> studentCourses = service.searchStudentCourseList();
        return converter.convertStudentdetails(students, studentCourses);
    }


    @GetMapping("/studentcourseList")
    public List<StudentCourse> getStudentsCourseList() {
        return service.searchStudentCourseList();
    }

    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourses(Arrays.asList(new StudentCourse()));
        model.addAttribute("studentDetail", studentDetail);
        return "registerStudent";

    }

    @PostMapping("/registerStudent")
    public ResponseEntity<String> registerStudent(@RequestBody StudentDetail studentDetail) {
        service.registerStudent(studentDetail);
        return ResponseEntity.ok("登録処理が成功しました");
    }

    @GetMapping("/studentsCourseList")
    public List<StudentCourse> getStudentCourseList() {
        return service.searchStudentCourseList();
    }

    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable String id, Model model) {
        StudentDetail studentDetail = service.searchStudent(id);
        model.addAttribute("studentDetail", studentDetail);
        return "updateStudent";
    }

    @PostMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました");
    }
}


