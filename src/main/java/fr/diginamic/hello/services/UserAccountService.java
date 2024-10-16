package fr.diginamic.hello.services;

import fr.diginamic.hello.model.UserAccount;
import fr.diginamic.hello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        this.repository.save(new UserAccount("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
        this.repository.save(new UserAccount("user", passwordEncoder.encode("user"), "ROLE_USER"));
    }

}
