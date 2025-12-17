package com.example.mysql_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import co.elastic.apm.api.CaptureSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(path = "/friend")
public class MainController {
    @Autowired
    private FriendRepository friendRepository;

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @CaptureSpan(value = "delay")
    public void delay(int length) {
        logger.info("going to sleep for " + length + "ms.");
        try {
            Thread.sleep(length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("finished sleep");
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewFriend(@RequestParam String name) {
        logger.info("adding new friend: " + name + ".");
        delay(1000);
        Friend friend = new Friend();
        friend.setName(name);
        friendRepository.save(friend);
        logger.info("added: " + name);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Friend> getAllFriends() {
        return friendRepository.findAll();
    }
}
