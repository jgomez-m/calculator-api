package com.truenorth;

import com.truenorth.entity.Operation;
import com.truenorth.entity.User;
import com.truenorth.repository.OperationRepository;
import com.truenorth.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    OperationRepository operationRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Starting application: Creating new entities");

        User user1 = new User();
        User user2 = new User();
        user1.setUsername("juliangomez@gmail.com"); user1.setPassword("123"); user1.setStatus(User.Status.ACTIVE); user1.setBalance(1000.0);
        user2.setUsername("test@test.com"); user2.setPassword("abc"); user2.setStatus(User.Status.ACTIVE); user2.setBalance(1000.0);
        userRepository.save(user1);
        userRepository.save(user2);

        Operation op1 = new Operation(Operation.Type.ADDITION, 100);
        Operation op2 = new Operation(Operation.Type.SUBTRACTION, 100);
        Operation op3 = new Operation(Operation.Type.MULTIPLICATION, 1000);
        Operation op4 = new Operation(Operation.Type.DIVISION, 1000);
        Operation op5 = new Operation(Operation.Type.SQUARE_ROOT, 10);
        Operation op6 = new Operation(Operation.Type.RANDOM_STRING, 1);
        operationRepository.save(op1);
        operationRepository.save(op2);
        operationRepository.save(op3);
        operationRepository.save(op4);
        operationRepository.save(op5);
        operationRepository.save(op6);

        System.out.println("All users:");
        userRepository.findAll().forEach(x -> System.out.println(x.getUsername()));
        System.out.println("\nAll operations:");
        operationRepository.findAll().forEach(x -> System.out.println(x.getType()));
    }
}
