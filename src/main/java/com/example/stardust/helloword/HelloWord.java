package com.example.stardust.helloword;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Althaea
 * @Date: 2022/10/31 21:45
 */

@RestController
@RequestMapping("/hello")
public class HelloWord {
    @RequestMapping("/helloword")
    public String helloWord() {
        return "Hello Word!!!";
    }
}