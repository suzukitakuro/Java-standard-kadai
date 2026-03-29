package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 受講生コース情報を扱うオブジェクト
 *
 */

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {
    private String courseId;
    private String id;
    private String courseName;
    @NotNull
    private LocalDateTime courseStartAt;
    @NotNull
    @Future(message = "過去の日付を入力しないでください")
    private LocalDateTime courseEndAt;
    @NotEmpty
    private String status;

}

