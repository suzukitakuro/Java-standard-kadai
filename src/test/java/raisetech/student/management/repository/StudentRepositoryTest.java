package raisetech.student.management.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MybatisTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository sut;

    @Test
    void 受講生の全件検索ができること() {
        List<Student> actual = sut.search();
        assertThat(actual.size()).isEqualTo(10);

    }


    @Test
    void 受講生の検索() {
        String id = "2";
        Student actual = sut.searchStudent(id);
        assertThat(actual.getId()).isEqualTo(id);

    }

    @Test
    void 受講生のコース情報の全件の検索ができること() {
        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual.size()).isEqualTo(12);

    }

    @Test
    void 受講生IDに紐づく受講生コース情報を検索() {
        String id = "2";
        List<StudentCourse> actual = sut.searchStudentCourse(id);
        assertThat(actual.size()).isEqualTo(2);

    }


    @Test
    void 受講生の登録ができること() {
        Student student = new Student();
        student.setName("藤浪晋太郎");
        student.setKanaName("フジナミシンタロウ");
        student.setNickname("フジ");
        student.setEmail("Fuji@gmail.com");
        student.setArea("大阪府堺市");
        student.setAge(31);
        student.setSex("男性");
        student.setRemark("");
        student.setDeleted(false);

        sut.registerStudent(student);

        List<Student> actual = sut.search();

        assertThat(actual.size()).isEqualTo(11);


    }

    @Test
    void 受講生の更新ができること() {
        Student student = sut.searchStudent("2");
        student.setAge(28);
        sut.updateStudent(student);

        Student actual = sut.searchStudent("2");
        assertThat(actual.getAge()).isEqualTo(28);

    }

    @Test
    void 受講生コース情報を更新ができること() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseName("AWSコース");
        sut.updateStudentCourse(studentCourse);

        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual.get(0).getCourseName()).isEqualTo("AWSコース");


    }

}