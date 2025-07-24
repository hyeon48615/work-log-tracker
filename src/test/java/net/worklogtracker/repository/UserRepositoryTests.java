package net.worklogtracker.repository;

import net.worklogtracker.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        UserEntity user = UserEntity.builder()
                .username("user001")
                .password(passwordEncoder.encode("a123456789!"))
                .build();

        userRepository.save(user);

        List<UserEntity> users = userRepository.findAll();
        System.out.println(users.toString());
    }
}
