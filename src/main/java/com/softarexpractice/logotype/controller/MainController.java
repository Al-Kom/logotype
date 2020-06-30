package com.softarexpractice.logotype.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Log
@Controller
public class MainController {

    // user profile page
    @GetMapping("/profile")
    public String profile(Map<String, Object> model) {
        return "profile";
    }
}
