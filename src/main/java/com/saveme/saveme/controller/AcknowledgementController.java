package com.saveme.saveme.controller;

import com.saveme.saveme.model.Acknowledgement;
import com.saveme.saveme.service.AcknowledgementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acknowledgements")
public class AcknowledgementController {

    @Autowired
    private AcknowledgementService acknowledgementService;

    @PostMapping
    public Acknowledgement createAcknowledgement(@RequestBody Acknowledgement acknowledgement) {
        return acknowledgementService.createAcknowledgement(acknowledgement);
    }

    @GetMapping
    public List<Acknowledgement> getAllAcknowledgements() {
        return acknowledgementService.getAllAcknowledgements();
    }

    @GetMapping("/{id}")
    public Acknowledgement getAcknowledgementById(@PathVariable Long id) {
        return acknowledgementService.getAcknowledgementById(id);
    }
}
