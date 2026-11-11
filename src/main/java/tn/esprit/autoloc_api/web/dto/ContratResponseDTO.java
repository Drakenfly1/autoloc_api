
package tn.esprit.autoloc_api.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratResponseDTO(
    Long idContrat,
    LocalDate dateSignature,
    BigDecimal montantTotal,
    Boolean valide
) {}
