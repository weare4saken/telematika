package com.skypro.telematika.controller;

import com.skypro.telematika.dto.LogIndication;
import com.skypro.telematika.model.Token;
import com.skypro.telematika.service.IndicationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indications")
public class IndicationController {

    private final IndicationService indicationService;

    public IndicationController(IndicationService indicationService) {
        this.indicationService = indicationService;
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
