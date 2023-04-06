package com.skypro.telematika.controller;

import com.skypro.telematika.dto.LogIndication;
import com.skypro.telematika.model.SerialSecret;
import com.skypro.telematika.model.Token;
import com.skypro.telematika.service.IndicationService;
import com.skypro.telematika.service.SerialSecretService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indications")
public class IndicationController {

    private final IndicationService indicationService;
    private final SerialSecretService serialSecretService;

    public IndicationController(IndicationService indicationService, SerialSecretService serialSecretService) {
        this.indicationService = indicationService;
        this.serialSecretService = serialSecretService;
    }

    @PostMapping("/login")
    public Token logIn(@RequestBody SerialSecret serialSecret) {
        return serialSecretService.generateToken(serialSecret.getSerial(), serialSecret.getSecret());
    }

    @PostMapping
    public void getIndication(@RequestBody LogIndication logIndication, Authentication authentication) {
        Token principal = (Token) authentication.getPrincipal();
        indicationService.save(
                principal.getSerialSecret(),
                logIndication
        );
    }

    @GetMapping("/{serial}")
    public Double getAvg(@PathVariable String serial) {
        return indicationService.calculateAvgIndication(serial);
    }

}
