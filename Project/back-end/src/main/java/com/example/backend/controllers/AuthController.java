package com.example.backend.controllers;


import com.example.backend.dto.TailorDto;
import com.example.backend.helpers.JwtUtils;
import com.example.backend.requests.AuthRequest;
import com.example.backend.requests.TailorRequest;
import com.example.backend.responses.AuthResponse;
import com.example.backend.responses.TailorResponse;
import com.example.backend.services.tailor.TailorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private TailorService tailorService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    private void authenticationManager(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        TailorDto tailorDto = new TailorDto();
        BeanUtils.copyProperties(authRequest, tailorDto);
        TailorDto loggedInTailor = tailorService.login(tailorDto);
        TailorResponse tailorResponse = new TailorResponse();
        AuthResponse authResponse = new AuthResponse();
        BeanUtils.copyProperties(loggedInTailor, tailorResponse);
        authResponse.setTailorResponse(tailorResponse);

        if(authResponse != null) {
            authenticationManager(tailorDto.getEmail(), tailorDto.getPassword());
            final UserDetails userDetails = new User(tailorDto.getEmail(), tailorDto.getPassword(), Collections.singleton(new SimpleGrantedAuthority(tailorDto.getRole())));
            authResponse.setToken(jwtUtils.generateToken(userDetails));
            authResponse.setMessage("Login successful");
            return ResponseEntity.ok(authResponse);
        }

        authResponse.setMessage("Login failed");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody TailorRequest tailorRequest) {
        TailorDto tailorDto = new TailorDto();
        BeanUtils.copyProperties(tailorRequest, tailorDto);
        TailorDto createdTailor = tailorService.addTailor(tailorDto);
        TailorResponse tailorResponse = new TailorResponse();
        BeanUtils.copyProperties(createdTailor, tailorResponse);
        return ResponseEntity.ok("Tailor created successfully");
    }

}
