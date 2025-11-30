package raisetech.student.management.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;


@Getter
@Setter
@Data
public class StudentDetail {
    private Student student;
    private StudentCourse studentCourse;



}
