package com.skypro.telematika.service;

import com.skypro.telematika.dto.LogIndication;
import com.skypro.telematika.model.Indication;
import com.skypro.telematika.model.SerialSecret;
import com.skypro.telematika.repository.IndicationRepository;
import org.springframework.stereotype.Service;

@Service
public class IndicationService {

    private final IndicationRepository indicationRepository;

    public IndicationService(IndicationRepository indicationRepository) {
        this.indicationRepository = indicationRepository;
    }

    public Double calculateAvgIndication(String serial) {
        return indicationRepository.getAvgIndication(serial);
    }

    public void save(SerialSecret serialSecret, LogIndication logIndication) {
        Indication indication = new Indication();
        indication.setValue(logIndication.getValue());
        indication.setSerialSecret(serialSecret);
        indicationRepository.save(indication);
    }
}
