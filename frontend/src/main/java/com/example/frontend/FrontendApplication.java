package com.example.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
public class FrontendApplication {

    @GetMapping("/friend-list")
    public String friendList(Model model) {
        RestTemplate rest = new RestTemplate();
        Friend[] friends = rest.getForObject("http://localhost:8080/friend/all", Friend[].class);
        model.addAttribute("friends", friends);
        return "friends";
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }
}
