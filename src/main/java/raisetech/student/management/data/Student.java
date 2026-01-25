package raisetech.student.management.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 受講生を扱うオブジェクト
 */
@Getter
@Setter

public class Student {



    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String kanaName;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String area;
    private int age;
    @NotBlank
    private String sex;

    private String remark;
    private boolean isDeleted;
}
