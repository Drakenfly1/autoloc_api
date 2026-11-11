
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Equipement;
import tn.esprit.autoloc_api.web.dto.EquipementRequestDTO;
import tn.esprit.autoloc_api.web.dto.EquipementResponseDTO;

@Component
public class EquipementMapper {

    // Conversion Entité vers DTO
    public EquipementResponseDTO toDTO(Equipement equipement) {

        return new EquipementResponseDTO(
                equipement.getIdEquipement(),
                equipement.getLibelle()
        );
    }

    // Conversion DTO vers Entité
    public Equipement toEntity(EquipementRequestDTO dto) {

        Equipement equipement = new Equipement();

        equipement.setLibelle(dto.libelle());

        return equipement;
    }
}
