package com.sanlugar.sanluapp.auth;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanlugar.sanluapp.jwt.JwtService;
import com.sanlugar.sanluapp.model.Member;
import com.sanlugar.sanluapp.model.Role;
import com.sanlugar.sanluapp.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = memberRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getFirstName())
                .firstSurname(request.getLastName())
                .secondSurname(request.getLastName())
                .birthday(new Date())
                .role(Role.USER)
                .build();
        
        memberRepository.save(member);

        return AuthResponse.builder()
                .token(jwtService.getToken(member))
                .build();
    }

}
