package fr.diginamic.hello.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(){
        return "Je suis la classe de service et je vous dis Bonjour!";
    }

}
