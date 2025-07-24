package net.worklogtracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.worklogtracker.dto.UserResponse;
import net.worklogtracker.entity.UserEntity;
import net.worklogtracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<UserResponse> findUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user. id: " + username));

        return new User(entity.getUsername(), entity.getPassword(), List.of());
    }
}
