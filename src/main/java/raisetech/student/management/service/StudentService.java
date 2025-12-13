package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;


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

    @Transactional
    public void registerStudent(StudentDetail studentDetail){
        repository.registerStudent(studentDetail.getStudent());
        //コース情報登録も行う//
        for (StudentCourse studentCourse : studentDetail.getStudentCourses()){
            studentCourse.setCourseId(studentDetail.getStudent().getId());
            studentCourse.setCourseStart(LocalDate.now());
            studentCourse.setCourseEnd(LocalDate.now().plusYears(1));
            repository.registerStudentCourses(studentCourse);
        }

    }
}