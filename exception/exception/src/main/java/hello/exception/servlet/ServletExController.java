package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {

    @GetMapping("/test")
    public String test(){
        return "error-page/404";
    }

    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 발생!");
    }
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        log.info("404에러 전송");
        response.sendError(404);
    }
    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        log.info("500에러 전송");
        response.sendError(500);
    }
}
