package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;

import org.junit.jupiter.api.Test;


import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

public class BeanValidationTest {

    @Test
    void beanValidation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName(" "); //공백
        item.setPrice(0);
        item.setQuantity(10000);

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation = " + violation.getMessage());

        }
    }
    @Test
    void engineCheck(){
        try (Context context = Context.create("js")) {

            // 2 출력
            context.eval("js", "print( Math.min(2, 3) )");
        } catch (Exception e) {
            System.err.println();
        }

    }
}
