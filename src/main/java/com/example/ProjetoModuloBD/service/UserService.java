package com.example.ProjetoModuloBD.service;

import com.example.ProjetoModuloBD.dto.UserRequest;
import com.example.ProjetoModuloBD.model.User;
import com.example.ProjetoModuloBD.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;

    public void createUser(UserRequest userRequest){
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);
        authorityService.createAuthority(userRequest, user);
    }
}
