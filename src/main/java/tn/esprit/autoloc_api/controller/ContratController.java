package tn.esprit.autoloc_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc_api.domain.Contrat;
import tn.esprit.autoloc_api.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService contratService;
    @GetMapping
    public List<Contrat> findAll() { return contratService.findAll(); }
}

