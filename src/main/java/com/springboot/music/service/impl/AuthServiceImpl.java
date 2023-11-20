package com.springboot.music.service.impl;

import com.springboot.music.entity.Role;
import com.springboot.music.entity.User;
import com.springboot.music.exception.ResourceNotFoundException;
import com.springboot.music.payload.LoginDto;
import com.springboot.music.payload.SignUpDto;
import com.springboot.music.repository.RoleRepository;
import com.springboot.music.repository.UserRepository;
import com.springboot.music.security.JwtTokenProvider;
import com.springboot.music.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String signup(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())){
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }
        if (userRepository.existsByEmail(signUpDto.getEmail()))
        {
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }
        User user= new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Set<Role> roles= new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully!.";
    }


}
