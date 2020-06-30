package com.softarexpractice.logotype.controller;

import com.softarexpractice.logotype.model.Role;
import com.softarexpractice.logotype.model.User;
import com.softarexpractice.logotype.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Log
@Controller
public class SignUpController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(User user, Map<String, Object> model) {

        if (!user.getPassword().equals((user.getPasswordConfirm()))) {
            return "signup";
        }

        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb != null) {
            return "signup";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
