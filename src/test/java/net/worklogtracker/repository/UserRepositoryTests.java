package net.worklogtracker.repository;

import net.worklogtracker.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        UserEntity user = UserEntity.builder()
                .name("user001")
                .id("user001")
                .pwd(passwordEncoder.encode("a123456789!"))
                .build();

        userRepository.save(user);
    }
}
