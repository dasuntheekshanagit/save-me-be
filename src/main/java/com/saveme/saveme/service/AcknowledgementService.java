package com.saveme.saveme.service;

import com.saveme.saveme.model.Acknowledgement;
import com.saveme.saveme.repository.AcknowledgementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcknowledgementService {

    @Autowired
    private AcknowledgementRepository acknowledgementRepository;

    public Acknowledgement createAcknowledgement(Acknowledgement acknowledgement) {
        return acknowledgementRepository.save(acknowledgement);
    }

    public List<Acknowledgement> getAllAcknowledgements() {
        return acknowledgementRepository.findAll();
    }

    public Acknowledgement getAcknowledgementById(Long id) {
        return acknowledgementRepository.findById(id).orElse(null);
    }
}
