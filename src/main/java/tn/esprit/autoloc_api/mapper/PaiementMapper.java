
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Paiement;
import tn.esprit.autoloc_api.web.dto.PaiementResponseDTO;

@Component
public class PaiementMapper {

    public PaiementResponseDTO toDTO(Paiement paiement) {

        Long idContrat = paiement.getContrat() != null
                ? paiement.getContrat().getIdContrat()
                : null;

        return new PaiementResponseDTO(
                paiement.getIdPaiement(),
                paiement.getMontant(),
                paiement.getDatePaiement(),
                paiement.getModePaiement(),
                idContrat
        );
    }
}
