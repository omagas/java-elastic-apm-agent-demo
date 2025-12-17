package com.example.mysql_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import co.elastic.apm.api.CaptureSpan;

@Controller
@RequestMapping(path = "/friend")
public class MainController {
    @Autowired
    private FriendRepository friendRepository;

    @CaptureSpan(value = "delay")
    public void delay(int length) {
        try {
            Thread.sleep(length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewFriend(@RequestParam String name) {
        delay(1000);
        Friend friend = new Friend();
        friend.setName(name);
        friendRepository.save(friend);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Friend> getAllFriends() {
        return friendRepository.findAll();
    }
}
