
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Agence;
import tn.esprit.autoloc_api.web.dto.AgenceRequestDTO;
import tn.esprit.autoloc_api.web.dto.AgenceResponseDTO;

@Component
public class AgenceMapper {

    // Conversion Entité vers DTO
    public AgenceResponseDTO toDTO(Agence agence) {

        return new AgenceResponseDTO(
                agence.getIdAgence(),
                agence.getNom(),
                agence.getVille(),
                agence.getAdresse(),
                agence.getTelephone()
        );
    }

    // Conversion DTO vers Entité
    public Agence toEntity(AgenceRequestDTO dto) {

        Agence agence = new Agence();

        agence.setNom(dto.nom());
        agence.setVille(dto.ville());
        agence.setAdresse(dto.adresse());
        agence.setTelephone(dto.telephone());

        return agence;
    }
}
