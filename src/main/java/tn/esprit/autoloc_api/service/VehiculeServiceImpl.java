
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc_api.domain.Agence;
import tn.esprit.autoloc_api.domain.Vehicule;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.VehiculeMapper;
import tn.esprit.autoloc_api.repository.IAgenceRepository;
import tn.esprit.autoloc_api.repository.IVehiculeRepository;
import tn.esprit.autoloc_api.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc_api.web.dto.VehiculeResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    @Transactional
    public VehiculeResponseDTO create(VehiculeRequestDTO dto) {

        Vehicule vehicule = vehiculeMapper.toEntity(dto);

        vehicule.setAgence(trouverAgence(dto.idAgence()));

        Vehicule saved = vehiculeRepository.save(vehicule);

        return vehiculeMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public VehiculeResponseDTO findById(Long id) {

        Vehicule vehicule = rechercherVehicule(id);

        return vehiculeMapper.toDTO(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResponseDTO> findAll() {

        return vehiculeRepository.findAll()
                .stream()
                .map(vehiculeMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public VehiculeResponseDTO update(
            Long id, VehiculeRequestDTO dto) {

        Vehicule existant = rechercherVehicule(id);

        existant.setImmatriculation(dto.immatriculation());
        existant.setMarque(dto.marque());
        existant.setModele(dto.modele());
        existant.setCategorie(dto.categorie());
        existant.setTarifJournalier(dto.tarifJournalier());
        existant.setStatut(dto.statut());

        existant.setAgence(trouverAgence(dto.idAgence()));

        Vehicule updated = vehiculeRepository.save(existant);

        return vehiculeMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Véhicule", id);
        }

        vehiculeRepository.deleteById(id);
    }

    private Vehicule rechercherVehicule(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Véhicule", id)
                );
    }

    private Agence trouverAgence(Long idAgence) {

        if (idAgence == null) {
            return null;
        }

        return agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Agence", idAgence)
                );
    }
}
