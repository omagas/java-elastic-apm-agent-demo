package com.example.trafficsim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class TrafficsimApplication {
    private static String getFriendList() {
        RestTemplate rest = new RestTemplate();
        return rest.getForObject(
                "http://localhost:8081/friend-list", String.class);
    }

    @GetMapping("/simulate-traffic")
    public String simulateTraffic(@RequestParam(value = "num-events", defaultValue = "10") int numEvents) {
        for (int i = 0; i < numEvents; i++) {
            try {
                int t = (int) (Math.random() * 2000);
                System.out.println("TBT: " + t);
                Thread.sleep(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getFriendList());
        }
        return "simulated " + numEvents + " events.\n";
    }

    public static void main(String[] args) {
        SpringApplication.run(TrafficsimApplication.class, args);
    }
}
