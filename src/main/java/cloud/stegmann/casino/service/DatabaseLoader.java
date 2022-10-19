package cloud.stegmann.casino.service;

import cloud.stegmann.casino.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private final UserRepository users;
    @Autowired
    private final AccountRepository account;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, AccountRepository account){
        this.users = userRepository;
        this.account = account;
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
