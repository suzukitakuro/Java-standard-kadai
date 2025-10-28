package raisetech.student.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private StudentRepository repository;


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/studentcourseList")
    public List<Studentcourse> getStudentcourseList() {
        return repository.search();
    }
}


