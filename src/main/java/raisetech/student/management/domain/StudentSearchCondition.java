package raisetech.student.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class StudentSearchCondition {

    private Integer id;
    private String name;
    private String email;
    private String area;
    private Integer age;
    private String sex;

    public StudentSearchCondition(Integer id, String name, String email,
                                  String area, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.area = area;
        this.age = age;
        this.sex = sex;
    }
}



