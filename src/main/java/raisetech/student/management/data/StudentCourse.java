package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 受講生コース譲歩を扱うオブジェクト
 *
 */

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {
    private String courseId;
    private String id;
    private String courseName;
    private LocalDate courseStart;
    private LocalDate courseEnd;

}

