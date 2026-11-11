
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc_api.domain.Maintenance;
import tn.esprit.autoloc_api.domain.Vehicule;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.MaintenanceMapper;
import tn.esprit.autoloc_api.repository.IMaintenanceRepositorty;
import tn.esprit.autoloc_api.repository.IVehiculeRepository;
import tn.esprit.autoloc_api.web.dto.MaintenanceRequestDTO;
import tn.esprit.autoloc_api.web.dto.MaintenanceResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepositorty maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    @Transactional
    public MaintenanceResponseDTO create(MaintenanceRequestDTO dto) {

        verifierDates(dto);

        Maintenance maintenance = maintenanceMapper.toEntity(dto);

        maintenance.setVehicule(trouverVehicule(dto.idVehicule()));

        Maintenance saved = maintenanceRepository.save(maintenance);

        return maintenanceMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MaintenanceResponseDTO findById(Long id) {

        Maintenance maintenance = rechercherMaintenance(id);

        return maintenanceMapper.toDTO(maintenance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceResponseDTO> findAll() {

        return maintenanceRepository.findAll()
                .stream()
                .map(maintenanceMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public MaintenanceResponseDTO update(
            Long id, MaintenanceRequestDTO dto) {

        Maintenance existante = rechercherMaintenance(id);

        verifierDates(dto);

        existante.setDateDebut(dto.dateDebut());
        existante.setDateFin(dto.dateFin());
        existante.setDescription(dto.description());
        existante.setVehicule(trouverVehicule(dto.idVehicule()));

        Maintenance updated = maintenanceRepository.save(existante);

        return maintenanceMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }

        maintenanceRepository.deleteById(id);
    }

    private Maintenance rechercherMaintenance(Long id) {

        return maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Maintenance", id)
                );
    }

    private Vehicule trouverVehicule(Long idVehicule) {

        if (idVehicule == null) {
            return null;
        }

        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Véhicule", idVehicule)
                );
    }

    private void verifierDates(MaintenanceRequestDTO dto) {

        if (dto.dateDebut() == null) {
            throw new IllegalArgumentException(
                    "La date de début de maintenance est obligatoire"
            );
        }

        if (dto.dateFin() != null
                && dto.dateFin().isBefore(dto.dateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début"
            );
        }
    }
}
