package es.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {

    @GetMapping({"/"})
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
