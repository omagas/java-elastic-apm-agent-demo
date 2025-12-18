package com.example.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class FrontendApplication {
    private static String getFriendList() {
        RestTemplate rest = new RestTemplate();
        return rest.getForObject("http://localhost:8080/friend/all", String.class);
    }

    @GetMapping("/friend-list")
    public String friendList() {
        return getFriendList();
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }
}
