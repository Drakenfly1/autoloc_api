
package tn.esprit.autoloc_api.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc_api.service.IReservationService;
import tn.esprit.autoloc_api.web.dto.ReservationResponseDTO;
import tn.esprit.autoloc_api.web.dto.ReservationWithContratRequestDTO;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping("/avec-contrat")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO creerAvecContrat(
            @Valid @RequestBody
            ReservationWithContratRequestDTO requestDTO) {

        return reservationService
                .creerReservationEtContratAssocie(requestDTO);
    }
}
