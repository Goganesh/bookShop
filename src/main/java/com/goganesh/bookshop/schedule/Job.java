package com.goganesh.bookshop.schedule;

import com.goganesh.bookshop.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Job {

    private final UserRegisterService userRegisterService;

    //@Scheduled(fixedRate = 1000)// todo cron = "0 0 12 15 * ?")
    public void deleteTempUsers() {
        System.out.println("fdfsdsdf");//todo delete temp users
    }
}
