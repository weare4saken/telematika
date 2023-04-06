package com.skypro.telematika.repository;

import com.skypro.telematika.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {


    void deleteAllByExpiredDateIsBefore(Instant now);

}
