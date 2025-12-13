package raisetech.student.management.domain;

import lombok.Getter;
import lombok.Setter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;

@Getter
@Setter
public class StudentDetail {

    private Student student;
    private List<StudentCourse> studentCourses;
}
