package com.skypro.telematika.sheduler;

import com.skypro.telematika.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;

@Slf4j
@Component
public class ClearTokensJob {

    private final TokenRepository tokenRepository;

    public ClearTokensJob(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void clearTokens() {
        log.info("Clear tokens");
        tokenRepository.deleteAllByExpiredDateIsBefore(Instant.now());
    }

}
