package com.ilyamorozov.bootpark.controller;

import com.ilyamorozov.bootpark.dto.AuthResponceDto;
import com.ilyamorozov.bootpark.dto.LoginDto;
import com.ilyamorozov.bootpark.dto.RegisterDto;
import com.ilyamorozov.bootpark.entity.Role;
import com.ilyamorozov.bootpark.entity.UserEntity;
import com.ilyamorozov.bootpark.repository.RoleRepository;
import com.ilyamorozov.bootpark.repository.UserRepository;
import com.ilyamorozov.bootpark.security.JWTGenerator;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponceDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        AuthResponceDto authResponceDto = new AuthResponceDto(token);
        UserEntity userEntity = userRepository.findByUsername(loginDto.getUsername()).get();  // надо из юзера достать его роли
        authResponceDto.setRoles(userEntity.getRoles()  // т.е. не сами роли, а их названия
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList()));
        authResponceDto.setId(userEntity.getId());  // добавили айдишник, чтобы можно было создавать BookedSlot и получать BookedSlot
        return new ResponseEntity<>(authResponceDto, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponceDto> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        // Создаём аутентификацию для генерации токена
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerDto.getUsername(),
                        registerDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        // Возвращаем токен, роли и ID пользователя
        AuthResponceDto authResponsDto = new AuthResponceDto(token);
        authResponsDto.setRoles(user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList()));
        authResponsDto.setId(user.getId());

        return new ResponseEntity<>(authResponsDto, HttpStatus.OK);
    }
}
