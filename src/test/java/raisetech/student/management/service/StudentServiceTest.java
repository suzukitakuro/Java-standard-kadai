package raisetech.student.management.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    private StudentService sut;

    @BeforeEach
    void before() {
        sut = new StudentService(repository, converter);
    }

    @Test
    void 受講生詳細の一覧検索＿リポジトリとコンバータの処理が適切によびだされていること() {
        StudentService sut = new StudentService(repository, converter);
        List<Student> studentList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();
        when(repository.search()).thenReturn(studentList);
        when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

        sut.searchStudentList();

        verify(repository, times(1)).search();
        verify(repository, times(1)).searchStudentCourseList();
        verify(converter, times(1)).convertStudentdetails(studentList, studentCourseList);
    }

    @Test
    void 受講生詳細の検索＿リポジトリとコンバータの処理が適切によびだされていること() {
        StudentService sut = new StudentService(repository, converter);
        Student student = new Student();
        String id = "1";
        student.setId(id);
        List<StudentCourse> studentCourseList = new ArrayList<>();
        when(repository.searchStudent(id)).thenReturn(student);
        when(repository.searchStudentCourse(id)).thenReturn(studentCourseList);

        StudentDetail actual = sut.searchStudent(id);

        verify(repository, times(1)).searchStudent(id);
        verify(repository, times(1)).searchStudentCourse("1");
        assertEquals(actual.getStudent().getId(), student.getId());
    }

    @Test
    void 受講生詳細の登録＿リポジトリとコンバータの処理が適切によびだされていること() {
        StudentService sut = new StudentService(repository, converter);
        Student student = new Student();
        student.setName("鈴木");
        StudentCourse studentCourse = new StudentCourse();
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentCourseList(List.of(studentCourse));


        StudentDetail actual = sut.registerStudent(studentDetail);

        verify(repository, times(1)).registerStudent(student);
        verify(repository, times(1)).registerStudentCourse(studentCourse);

        assertSame(studentDetail, actual);


    }

    @Test
    void 受講生詳細の更新＿リポジトリとコンバータの処理が適切によびだされていること() {
        StudentService sut = new StudentService(repository, converter);
        Student student = new Student();
        student.setId("1");
        StudentCourse studentCourse = new StudentCourse();
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentCourseList(List.of(studentCourse));


        sut.updateStudent(studentDetail);

        verify(repository, times(1)).updateStudent(student);
        verify(repository, times(1)).updateStudentCourse(studentCourse);

    }


    @Test
    void 受講生詳細の初期化＿リポジトリとコンバータの処理が適切によびだされていること() {
        StudentService sut = new StudentService(repository, converter);
        String id = "999";
        Student student = new Student();
        student.setId(id);
        StudentCourse studentCourse = new StudentCourse();

        sut.initStudentsCourse(studentCourse, student.getId());

        assertEquals("999", studentCourse.getId());
        assertEquals(LocalDateTime.now().getHour(),studentCourse.getCourseStartAt().getHour());
        assertEquals(LocalDateTime.now().plusYears(1).getYear(),studentCourse.getCourseEndAt().getYear());


    }

}