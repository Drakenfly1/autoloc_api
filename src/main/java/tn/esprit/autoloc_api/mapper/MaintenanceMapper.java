
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Maintenance;
import tn.esprit.autoloc_api.web.dto.MaintenanceRequestDTO;
import tn.esprit.autoloc_api.web.dto.MaintenanceResponseDTO;

@Component
public class MaintenanceMapper {

    // Conversion Entité vers DTO
    public MaintenanceResponseDTO toDTO(Maintenance maintenance) {

        Long idVehicule = maintenance.getVehicule() != null
                ? maintenance.getVehicule().getIdVehicule()
                : null;

        return new MaintenanceResponseDTO(
                maintenance.getIdMaintenance(),
                maintenance.getDateDebut(),
                maintenance.getDateFin(),
                maintenance.getDescription(),
                idVehicule
        );
    }

    // Conversion DTO vers Entité
    public Maintenance toEntity(MaintenanceRequestDTO dto) {

        Maintenance maintenance = new Maintenance();

        maintenance.setDateDebut(dto.dateDebut());
        maintenance.setDateFin(dto.dateFin());
        maintenance.setDescription(dto.description());

        return maintenance;
    }
}
