package raisetech.student.management.contoroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.domain.StudentSearchCondition;
import raisetech.student.management.exception.TestException;
import raisetech.student.management.service.StudentService;


import java.util.List;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるcontrollerのクラスです。
 */
@Validated
@RestController

public class StudentController {


    private StudentService service;


    @Autowired
    public StudentController(StudentService service) {
        this.service = service;

    }

    /**
     * 受講生詳細の一覧検索です
     * 全権検索を行うので、条件指定は行わないものになります。
     *
     * @return　受講生詳細一覧(全件)
     */
    @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /**
     * 受講生検索です
     * IDに紐づく任意の受講生情報を取得します。
     *
     * @param condition 受講生条件
     * @return 受講生
     */
    @Operation(summary = "受講生検索", description = "検索条件を指定して様々な条件で受講生を検索します。", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400", description = "リクエストエラー", content = @Content())})
    @GetMapping("/students")
    public List<StudentDetail> searchStudents(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String sex) {

        StudentSearchCondition condition = new StudentSearchCondition();
        condition.setId(id);
        condition.setName(name);
        condition.setEmail(email);
        condition.setArea(area);
        condition.setAge(age);
        condition.setSex(sex);

        return service.searchStudents(condition);
    }

    /**
     * 受講生詳細の登録を行います。
     *
     * @param studentDetail
     * @return　実行結果
     */
    @Operation(summary = "受講生登録", description = "受講生の登録をします。"
            , responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400", description = "リクエストエラー", content = @Content())})
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
        StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);

    }


    /**
     * 受講生詳細の更新を行います。　キャンセルフラグの更新も行います(論理削除)
     *
     * @param studentDetail
     * @return　実行結果
     */
    @Operation(summary = "受講生詳細の更新", description = "受講生詳細の更新をします。",
            responses = {@ApiResponse(responseCode = "200", description = "更新処理が成功しました"), @ApiResponse(responseCode = "400", description = "リクエストエラー", content = @Content())})
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました");
    }


    @ExceptionHandler(TestException.class)
    public ResponseEntity<String> handleTestException(TestException ex) {
        //ログ出力
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}


