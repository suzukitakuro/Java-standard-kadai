package raisetech.student.management.contoroller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
        when(service.searchStudentList()).thenReturn(List.of(new StudentDetail()));
        mockMvc.perform(MockMvcRequestBuilders.get("/studentList")).andExpect(status().isOk()).andExpect(content().json("[{\"student\":null,\"studentCourseList\":null}]"));


        verify(service, times(1)).searchStudentList();
    }

    @Test
    void 受講生検索が実行できること() throws Exception {
        Student student = new Student();
        String id = "1";
        student.setId(id);
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        when(service.searchStudent(id)).thenReturn(studentDetail);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", id)).andExpect(status().isOk());


        verify(service, times(1)).searchStudent(id);
        assertEquals(studentDetail.getStudent().getId(), student.getId());
    }


    @Test
    void 受講生の詳細の受講生で入力チェックに異常が発生しないこと() {
        Student student = new Student();
        student.setId("1");
        student.setName("牧秀吾");
        student.setKanaName("マキシュウゴ");
        student.setNickname("まっきー");
        student.setEmail("test@example.com");
        student.setArea("横浜");
        student.setSex("男性");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);


        assertThat(violations.size()).isEqualTo(0);
    }


    @Test
    void 受講生の詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() {
        Student student = new Student();
        student.setId("テストです。");
        student.setName("牧秀吾");
        student.setKanaName("マキシュウゴ");
        student.setNickname("まっきー");
        student.setEmail("test@example.com");
        student.setArea("横浜");
        student.setSex("男性");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).extracting("message").contains("数字のみ入力してください。");

    }

    @Test
    void 受講生の詳細の受講生でIDが1より低い数字を用いて入力してきた時に入力チェックに掛かること() {
        Student student = new Student();
        student.setId("0");
        student.setName("牧秀吾");
        student.setKanaName("マキシュウゴ");
        student.setNickname("まっきー");
        student.setEmail("test@example.com");
        student.setArea("横浜");
        student.setSex("男性");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").contains("1以上入力してください。");


    }

    @Test
    void 受講生の詳細の受講生でEmailがメール形式じゃない形式で入力してきた時に入力チェックに掛かること() {
        Student student = new Student();
        student.setId("1");
        student.setName("牧秀吾");
        student.setKanaName("マキシュウゴ");
        student.setNickname("まっきー");
        student.setEmail("テスト");
        student.setArea("横浜");
        student.setSex("男性");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").contains("メール形式で入力してください。");

    }

    @Test
    void 受講生のコース情報で終了日が過去の日付で入力してきた時に入力チェックに掛かること() {
        StudentCourse studentcourse = new StudentCourse();
        studentcourse.setCourseId("1");
        studentcourse.setId("1");
        studentcourse.setCourseName("Javaコース");
        studentcourse.setCourseStartAt(LocalDateTime.now());
        studentcourse.setCourseEndAt(LocalDateTime.of(2025, 9, 10, 0, 0));

        Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentcourse);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").contains("過去の日付を入力しないでください");
    }

    @Test
    void 受講生詳細の登録が実行できて空で返ってくること() throws Exception {
        mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
                """
                     {   
                        
                                          "student":{
                                              "id":"2",
                                              "name":"牧秀吾",
                                              "kanaName":"シュウゴマキ",
                                              "nickname":"まっきー",
                                              "email":"shuugo.maki@gmail.com",
                                              "area":"長野県中野市",
                                              "age":"27",
                                              "sex":"男",
                                              "remark":"",
                                              "isDeleted":"true"
                        
                        
                        
                                },
                                "studentCourseList":[
                                {
                                    "courseName":"AWSコース"
                        
                                }
                        
                            ]
                        
                        }
                        """)).andExpect(status().isOk());
        verify(service, times(1)).registerStudent(any());


    }

    @Test
    void 受講生詳細の更新が実行できて空で返ってくること() throws Exception {
        mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content("""
                
                              {
                                  "student":{
                                      "id":"12",
                                      "name":"牧秀吾",
                                      "kanaName":"シュウゴマキ",
                                      "nickname":"まっきー",
                                      "email":"shuugo.maki@gmail.com",
                                      "area":"長野県中野市",
                                      "age":"27",
                                      "sex":"男",
                                      "remark":"",
                                      "isDeleted":"true"
                
                
                                  },
                                  "studentCourseList":[
                                  {
                                  }
                
                                  ]
                
                              }
                """)).andExpect(status().isOk());
        verify(service, times(1)).updateStudent(any());


    }


}
