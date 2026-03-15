package raisetech.student.management.controller.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentConverterTest {



    private List<Student> studentList;



    private List<StudentCourse> studentCourseList;

    private StudentConverter sut;

    @BeforeEach
    void before() {
        sut = new StudentConverter();
    }


    @Test
    void 受講生に紐づく受講生コース情報をマッピングできること() throws Exception{
        String id = "555";
        Student student = new Student();
        student.setId(id);
        String courseId = "913";
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(courseId);
        studentCourse.setId(id);

        studentList = new ArrayList<>();
        studentCourseList = new ArrayList<>();

        studentList.add(student);
        studentCourseList.add(studentCourse);

        List<StudentDetail> studentDetails = sut.convertStudentdetails(studentList, studentCourseList);
        Assertions.assertEquals(1,studentDetails.size());

        StudentDetail detail = studentDetails.get(0);

        Assertions.assertEquals(student, detail.getStudent());
        Assertions.assertEquals(1, detail.getStudentCourseList().size());
        Assertions.assertEquals(studentCourse, detail.getStudentCourseList().get(0));


    }

    @Test
    void 受講生コース情報がない空のリストが作られること() throws Exception{
        String id = "333";
        Student student = new Student();
        student.setId(id);


        studentList = new ArrayList<>();
        studentCourseList = new ArrayList<>();

        studentList.add(student);


        List<StudentDetail> studentDetails = sut.convertStudentdetails(studentList, studentCourseList);
        Assertions.assertEquals(1,studentDetails.size());
        Assertions.assertEquals(0, studentDetails.get(0).getStudentCourseList().size());


    }

}