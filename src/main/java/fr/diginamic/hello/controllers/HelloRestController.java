package fr.diginamic.hello.controllers;

import fr.diginamic.hello.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloRestController {

    @Autowired
    private HelloService helloService;

    @GetMapping
    public String sayHello(){
        return this.helloService.sayHello();
    }
}
