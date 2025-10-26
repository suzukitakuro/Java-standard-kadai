package raisetech.student.management.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



    @Getter
    @Setter

    public class StudentCourse {
        private String courseId;
        private String id;
        private String courseName;
        private LocalDate courseStart;
        private LocalDate courseEnd;

    }

