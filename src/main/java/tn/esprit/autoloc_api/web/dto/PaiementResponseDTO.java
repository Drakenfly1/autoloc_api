
package tn.esprit.autoloc_api.web.dto;

import tn.esprit.autoloc_api.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaiementResponseDTO(

        Long idPaiement,

        BigDecimal montant,

        LocalDate datePaiement,

        ModePaiement modePaiement,

        Long idContrat

) {}
