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
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
        String response = authService.signup(signUpDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
