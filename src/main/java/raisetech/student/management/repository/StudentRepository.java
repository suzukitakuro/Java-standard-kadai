package raisetech.student.management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 *
 */


@Mapper
public interface StudentRepository {
    /**
     * 受講生の全体検索を行います。
     *
     * @return 受講生一覧(全件)
     */
    @Select("SELECT * FROM students WHERE isDeleted = false")
    List<Student> studentsearch();

    /**
     * 受講生の検索を行います。
     *
     * @param id　受講生ID
     * @return 受講生
     */
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findStudent(String id);


    /**
     * 受講生のコース情報の全体検索を行います。
     *
     * @return 受講生コース情報(全件)
     */
    @Select("SELECT * FROM students_courses ")
    List<StudentCourse> studentcoursesearch();


    /**
     * 受講生IDに紐づく受講生コース情報を検索します
     * @param studentId 受講生ID
     * @return 受講生IDに紐づく受講生コース情報
     */
    @Select("SELECT * FROM students_courses WHERE id = #{id}")
    List<StudentCourse> searchStudentCourse(String studentId);

    @Insert("INSERT INTO students(name, kanaName, nickname,email,area,age,sex,remark,isDeleted)"
            + "VALUES(#{name},#{kanaName},#{nickname},#{email},#{area},#{age},#{sex},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    @Insert(
            "INSERT INTO students_courses(courseId, courseName,courseStart,courseEnd)"
                    + "VALUES(#{courseId},#{courseName},#{courseStart},#{courseEnd})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentCourses(StudentCourse studentCourse);

    @Update("UPDATE students SET name = #{name}, kanaName = #{kanaName}, nickname = #{nickname},"
            + " email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(Student student);

    @Update("UPDATE students_courses SET courseName = #{courseName} WHERE id = #{id}")
    void updateStudentsCourses(StudentCourse studentCourse);

    @Update("UPDATE students SET isDeleted = true WHERE id = #{id}")
    void deleteStudent(String id);


}

