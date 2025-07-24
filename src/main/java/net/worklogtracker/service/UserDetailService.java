package net.worklogtracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.worklogtracker.entity.UserEntity;
import net.worklogtracker.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user. id: " + username));

        return new User(entity.getUsername(), entity.getPassword(),
                List.of(new SimpleGrantedAuthority(entity.getRole())));
    }
}
