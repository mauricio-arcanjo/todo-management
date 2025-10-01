package com.mauarcanjo.todo.service;

import com.mauarcanjo.todo.dto.LoginDto;
import com.mauarcanjo.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);

}
