package com.example.flow.api.v1;


import com.example.flow.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String testIndex() {
        return "this is first interface for testing, successfully";
    }

    @GetMapping("/index2")
    public String testIndex2() {
       throw new NotFoundException(10000);
    }
}
