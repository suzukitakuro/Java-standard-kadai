package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;

/**
 * 受講生情報を扱うリポジトリ
 * 全体検索や単体検索を行えるクラスです。
 */


@Mapper
public interface StudentRepository {
    /**
     * 全体検索します。
     *
     * @return 全体検索した受講生情報の一覧
     */
    @Select("SELECT * FROM students ")
    List<Student> studentsearch();


    @Select("SELECT * FROM students_courses ")
    List<StudentCourse> studentcoursesearch();




}

