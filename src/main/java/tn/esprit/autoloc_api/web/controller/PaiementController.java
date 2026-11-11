
package tn.esprit.autoloc_api.web.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc_api.service.IPaiementService;
import tn.esprit.autoloc_api.web.dto.PaiementResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    // Récupérer tous les paiements
    @GetMapping
    public List<PaiementResponseDTO> findAll() {

        return paiementService.findAll();
    }

    // Récupérer un paiement par son identifiant
    @GetMapping("/{id}")
    public PaiementResponseDTO findById(@PathVariable Long id) {

        return paiementService.findById(id);
    }
}
