package hello.thymleaf.basic;

import com.sun.net.httpserver.HttpContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/text-unescaped")
    public String textUnescape(Model model){
        model.addAttribute("data", "Hello <b>Spring</b>");
        model.addAttribute("image", "<img src=\"https://user-images.githubusercontent.com/104331549/215254343-49d2fe75-ca16-4380-ba4f-dabd373520b6.png\">");
        return "basic/text-unescaped";
    }


    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userA", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);
        model.addAllAttributes(map);

        return "basic/variable";
    }
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model){
        session.setAttribute("sessionData", "Hello Session");
//        model.addAttribute("request", request);
//        model.addAttribute("response", response);
//        model.addAttribute("session", session);
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public int getAge() {
            return age;
        }
    }
}
