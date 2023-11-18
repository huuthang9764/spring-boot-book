package com.springboot.music.controller;

import com.springboot.music.entity.Role;
import com.springboot.music.entity.User;
import com.springboot.music.payload.JWTAuthResponse;
import com.springboot.music.payload.LoginDto;
import com.springboot.music.payload.SignUpDto;
import com.springboot.music.repository.RoleRepository;
import com.springboot.music.repository.UserRepository;
import com.springboot.music.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
//
//        // add check for username exists in a DB
//        if(userRepository.existsByUsername(signUpDto.getUsername())){
//            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        // add check for email exists in DB
//        if(userRepository.existsByEmail(signUpDto.getEmail())){
//            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        // create user object
//        User user = new User();
//        user.setName(signUpDto.getName());
//        user.setUsername(signUpDto.getUsername());
//        user.setEmail(signUpDto.getEmail());
//        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
//
//        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
//        user.setRoles(Collections.singleton(roles));
//
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//
//    }

}
