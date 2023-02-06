package hello.itemservice.web.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
public class TestModel {
    private String name;
    private int age;
}