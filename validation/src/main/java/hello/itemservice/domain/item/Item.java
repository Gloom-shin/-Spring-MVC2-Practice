package hello.itemservice.domain.item;

import lombok.Data;
import org.graalvm.polyglot.Context;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ScriptAssert(lang = "javascript", script = "_this.startDate.before(_this.endDate)")
public class Item {

    private Long id;
    @NotBlank
    private String itemName;
    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;
    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    public void checkBenefit(){
        try (Context context = Context.create("js")) {

            // 2 출력
            context.eval("js", "_this.price * _this.quantity >= 10000");
        } catch (Exception e) {
            System.err.println();
        }
    }
}
