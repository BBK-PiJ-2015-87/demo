package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class WebAppController {

    @RequestMapping("/webapp")
    public String index(HttpServletResponse response) {
        return "hello";
    }
}
