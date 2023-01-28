package hello.thymleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring!");
        model.addAttribute("data", "Hello gloom!");
        model.addAttribute("data2", "Hello Spring2!");
        model.addAttribute("image", "<img src=\"https://user-images.githubusercontent.com/104331549/215254343-49d2fe75-ca16-4380-ba4f-dabd373520b6.png\">");


        return "basic/text-basic";
    }

}
