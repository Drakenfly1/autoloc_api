package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.PaiementResponseDTO;

import java.util.List;

public interface IPaiementService {

    PaiementResponseDTO findById(Long id);

    List<PaiementResponseDTO> findAll();
}
