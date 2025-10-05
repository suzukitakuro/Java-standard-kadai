package raisetech.student.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;

@SpringBootApplication
@RestController
public class Application {


    private Map<String, Integer> dnamemberinfo = new HashMap<>();

    {
        dnamemberinfo.put("蛯名", 28);
        dnamemberinfo.put("牧", 27);
        dnamemberinfo.put("筒香", 34);
        dnamemberinfo.put("宮崎", 36);
        dnamemberinfo.put("オースティン", 34);
        dnamemberinfo.put("佐野", 31);
        dnamemberinfo.put("ジャクソン", 29);
        dnamemberinfo.put("山本",27);
        dnamemberinfo.put("東",29);
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/dnamemberinfo")
    public Map<String, Integer> getMemberinfo() {
        return dnamemberinfo;
    }

    @GetMapping("/dnamembername")
    public Set<String> getdnamembername() {
        return dnamemberinfo.keySet();
    }

    @GetMapping("/dnamemberage")
    public Collection<Integer> getdnamemberage() {
        return dnamemberinfo.values();
    }

    @PostMapping("/dnamemberinfo")
    public void setmemberinfo(@RequestBody Map<String, Integer> newmember) {
        dnamemberinfo.putAll(newmember);
    }

    @PostMapping("/dnamembername")
    public void setmemberinfo(@RequestBody String key) {
        dnamemberinfo.put(key,null);
    }



}
