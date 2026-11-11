
package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratRequestDTO(

    @NotNull(message = "La date de signature est obligatoire")
    LocalDate dateSignature,

    @NotNull(message = "Le montant total est obligatoire")
    @DecimalMin(
        value = "0.0",
        message = "Le montant ne peut pas être négatif"
    )
    BigDecimal montantTotal,

    @NotNull(message = "La validité est obligatoire")
    Boolean valide

) {}
