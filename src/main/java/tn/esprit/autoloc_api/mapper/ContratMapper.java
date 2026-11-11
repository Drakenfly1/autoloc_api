
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc_api.domain.Contrat;
import tn.esprit.autoloc_api.web.dto.ContratRequestDTO;
import tn.esprit.autoloc_api.web.dto.ContratResponseDTO;

@Component
public class ContratMapper {

    // Entité vers DTO : réponse au client
    public ContratResponseDTO toDTO(Contrat contrat) {
        return new ContratResponseDTO(
                contrat.getIdContrat(),
                contrat.getDateSignature(),
                contrat.getMontantTotal(),
                contrat.getValide()
        );
    }

    // DTO vers Entité : données reçues du client
    public Contrat toEntity(ContratRequestDTO dto) {
        Contrat contrat = new Contrat();

        contrat.setDateSignature(dto.dateSignature());
        contrat.setMontantTotal(dto.montantTotal());
        contrat.setValide(dto.valide());

        return contrat;
    }
}
