package com.springboot.music.service;

import com.springboot.music.payload.LoginDto;
import com.springboot.music.payload.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String signup(SignUpDto signUpDto);
}
