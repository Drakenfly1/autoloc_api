
package tn.esprit.autoloc_api.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc_api.service.IContratService;
import tn.esprit.autoloc_api.web.dto.ContratRequestDTO;
import tn.esprit.autoloc_api.web.dto.ContratResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    // GET : récupérer tous les contrats
    @GetMapping
    public List<ContratResponseDTO> findAll() {
        return contratService.findAll();
    }

    // GET : récupérer un contrat par son ID
    @GetMapping("/{id}")
    public ContratResponseDTO findById(@PathVariable Long id) {
        return contratService.findById(id);
    }

    // POST : créer un nouveau contrat
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratResponseDTO create(
            @Valid @RequestBody ContratRequestDTO dto) {

        return contratService.create(dto);
    }

    // PUT : modifier un contrat existant
    @PutMapping("/{id}")
    public ContratResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ContratRequestDTO dto) {

        return contratService.update(id, dto);
    }

    // DELETE : supprimer un contrat
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}
