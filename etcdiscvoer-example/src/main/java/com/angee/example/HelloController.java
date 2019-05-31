package com.angee.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String replyHello() {
        return "hello";
    }
}
