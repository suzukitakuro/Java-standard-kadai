package raisetech.student.management;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter

public class Studentcourse {
    private String courseId;
    private String id;
    private String coursename;
    private LocalDate courseStart;
    private LocalDate courseEnd;

}


