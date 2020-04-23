package com.github.marcoscouto.service.impl;

import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.repository.UserRepository;
import com.github.marcoscouto.security.UserSS;
import com.github.marcoscouto.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("Erro UserSS", "Usuário não encontrado"));
        return new UserSS(user);
    }

}
