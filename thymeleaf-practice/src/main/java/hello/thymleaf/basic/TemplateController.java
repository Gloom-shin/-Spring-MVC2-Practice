package hello.thymleaf.basic;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }

    @GetMapping("/fragment")
    public String template(){
        return "template/fragment/fragmentMain";
    }

    @GetMapping("/layout")
    public String layout(){

        return "template/layout/layoutMain";
    }

    @GetMapping("layoutExtend")
    public String layoutExtend(){
        return "template/layoutExtend/layoutFile";
    }
}
