package com.skypro.telematika.service;

import com.skypro.telematika.model.Token;
import com.skypro.telematika.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final TokenRepository repository;

    public AuthenticationService(TokenRepository repository) {
        this.repository = repository;
    }

    public Optional<Token> findByUuid(String uuid) {
        Optional<Token> token = repository.findById(uuid);
        if(token.isPresent() && Instant.now().isBefore(token.get().getExpiredDate())) {
            return token;
        }
        return Optional.empty();
    }

}
