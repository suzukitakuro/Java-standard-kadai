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
    List<Student> search();

    /**
     * 受講生の検索を行います。
     *
     * @param id 受講生ID
     * @return 受講生
     */
    Student searchStudent(String id);


    /**
     * 受講生のコース情報の全体検索を行います。
     *
     * @return 受講生コース情報(全件)
     */
    List<StudentCourse> searchStudentCourseList();


    /**
     * 受講生IDに紐づく受講生コース情報を検索します
     *
     * @param studentId 受講生ID
     * @return 受講生IDに紐づく受講生コース情報
     */
    @Select("SELECT * FROM students_courses WHERE id = #{id}")
    List<StudentCourse> searchStudentCourse(String studentId);

    /**
     * 受講生を新規登録します。 IDに関しては自動採番を行う。
     *
     * @param student 受講生
     */
    @Insert("INSERT INTO students(name, kanaName, nickname,email,area,age,sex,remark,isDeleted)"
            + "VALUES(#{name},#{kanaName},#{nickname},#{email},#{area},#{age},#{sex},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    /**
     * 受講生コースを新規登録します。IDに関しては自動採番を行う。
     *
     * @param studentCourse 受講生コース
     */
    @Insert(
            "INSERT INTO students_courses(courseId, courseName,courseStart,courseEnd)"
                    + "VALUES(#{courseId},#{courseName},#{courseStart},#{courseEnd})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentCourse(StudentCourse studentCourse);

    /**
     * 受講生を更新します
     *
     * @param student 受講生
     *
     */
    @Update("UPDATE students SET name = #{name}, kanaName = #{kanaName}, nickname = #{nickname},"
            + " email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(Student student);

    /**
     * 受講生コース情報を更新します。
     *
     * @param studentCourse 受講生コース情報
     *
     */
    @Update("UPDATE students_courses SET courseName = #{courseName} WHERE id = #{id}")
    void updateStudentCourse(StudentCourse studentCourse);

    @Update("UPDATE students SET isDeleted = true WHERE id = #{id}")
    void deleteStudent(String id);


}

