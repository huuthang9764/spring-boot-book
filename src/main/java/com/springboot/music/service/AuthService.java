package com.springboot.music.service;

import com.springboot.music.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
