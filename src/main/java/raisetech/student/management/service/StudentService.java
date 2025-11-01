package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/studentList")
    public List<Student> searchStudentList() {
        return repository.studentsearch();

    }

    @GetMapping("/student courseList")
    public List<StudentCourse> searchStudentCourseList() {
        return repository.studentcoursesearch();
    }
}