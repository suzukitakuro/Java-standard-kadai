package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.domain.StudentSearchCondition;
import raisetech.student.management.exception.RegistorTestException;
import raisetech.student.management.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生情報の検索や登録・更新処理を行います。
 *
 */

@Service
public class StudentService {
    private StudentRepository repository;
    private StudentConverter converter;


    @Autowired
    public StudentService(StudentRepository repository, StudentConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    /**
     * 受講生一覧検索です
     * 全権検索を行うので、条件指定は行わないものになります。
     *
     * @return　受講生一覧(全件)
     */
    @GetMapping("/studentList")
    public List<StudentDetail> searchStudentList() {
        List<Student> studentList = repository.search();
        List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
        return converter.convertStudentdetails(studentList, studentCourseList);

    }

    /**
     * 受講生詳細検索です。
     * 検索条件を指定して様々な条件で受講生を検索します。
     *
     * @param condition 複数検索
     * @return　受講生詳細
     */
    public List<StudentDetail> searchStudents(StudentSearchCondition condition) {
        List<Student> students = repository.searchStudents(condition);

        return students.stream()
                .map(student -> {
                    List<StudentCourse> courses =
                            repository.searchStudentCourse(student.getId());
                    return new StudentDetail(student, courses);
                })
                .toList();
    }

    /**
     * 受講生の登録を行います。　受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値やコース開始日、コース終了日を設定します。
     *
     * @param studentDetail 受講生詳細
     * @return 登録情報を付与した受講生詳細
     */
    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        Student student = studentDetail.getStudent();
        if ("佐藤和貴".equals(studentDetail.getStudent().getName())) {
            throw new RegistorTestException("佐藤和貴は生徒として登録できません");
        }

        repository.registerStudent(student);
        studentDetail.getStudentCourseList().forEach(studentCourse -> {
            initStudentsCourse(studentCourse, student.getId());
            repository.registerStudentCourse(studentCourse);

        });
        return studentDetail;

    }

    /**
     * 受講生コース情報を登録する際の初期情報を設定する。
     *
     * @param studentCourse 受講生コース情報
     * @param student       受講生
     */

    void initStudentsCourse(StudentCourse studentCourse, int id) {
        LocalDateTime now = LocalDateTime.now();

        studentCourse.setId(id);
        studentCourse.setCourseStartAt(now);
        studentCourse.setCourseEndAt(now.plusYears(1));
    }

    /**
     * 受講生詳細の更新を行います。受講生の情報と受講生コース情報をそれぞれ更新します。
     *
     * @param studentDetail
     */
    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        studentDetail.getStudentCourseList()
                .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
    }

    @Transactional
    public void updateStudentCourseStatus(int id, String status) {
        repository.updateStudentCourseStatus(id, status);
    }


}
