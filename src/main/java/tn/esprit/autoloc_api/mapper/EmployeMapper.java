
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Employe;
import tn.esprit.autoloc_api.web.dto.EmployeRequestDTO;
import tn.esprit.autoloc_api.web.dto.EmployeResponseDTO;

@Component
public class EmployeMapper {

    // Entité vers DTO
    public EmployeResponseDTO toDTO(Employe employe) {

        Long idAgence = employe.getAgence() != null
                ? employe.getAgence().getIdAgence()
                : null;

        return new EmployeResponseDTO(
                employe.getIdEmploye(),
                employe.getNom(),
                employe.getPrenom(),
                employe.getRole(),
                idAgence
        );
    }

    // DTO vers Entité
    public Employe toEntity(EmployeRequestDTO dto) {

        Employe employe = new Employe();

        employe.setNom(dto.nom());
        employe.setPrenom(dto.prenom());
        employe.setRole(dto.role());

        return employe;
    }
}
