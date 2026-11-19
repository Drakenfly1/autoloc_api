
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc_api.domain.Contrat;
import tn.esprit.autoloc_api.web.dto.ContratRequestDTO;
import tn.esprit.autoloc_api.web.dto.ContratResponseDTO;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.ContratMapper;
import tn.esprit.autoloc_api.repository.IContratRepository;
import tn.esprit.autoloc_api.domain.Reservation;
import tn.esprit.autoloc_api.repository.IReservationRepository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc_api.web.dto.ContratDTO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final ContratMapper contratMapper;
    private final IReservationRepository reservationRepository;

    @Override
    public ContratResponseDTO create(ContratRequestDTO dto) {

        Contrat contrat = contratMapper.toEntity(dto);

        Contrat savedContrat = contratRepository.save(contrat);

        return contratMapper.toDTO(savedContrat);
    }

    @Override
    public ContratResponseDTO findById(Long id) {

        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id)
                );

        return contratMapper.toDTO(contrat);
    }

    @Override
    public List<ContratResponseDTO> findAll() {

        return StreamSupport
                .stream(contratRepository.findAll().spliterator(), false)
                .map(contratMapper::toDTO)
                .toList();
    }

    @Override
    public ContratResponseDTO update(
            Long id,
            ContratRequestDTO dto) {

        Contrat existant = contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id)
                );

        existant.setDateSignature(dto.dateSignature());
        existant.setMontantTotal(dto.montantTotal());
        existant.setValide(dto.valide());

        Contrat updatedContrat = contratRepository.save(existant);

        return contratMapper.toDTO(updatedContrat);
    }

    @Override
    public void deleteById(Long id) {

        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }

        contratRepository.deleteById(id);
    }


    @Override
    @Transactional
    public ContratDTO affecterContratAReservation(
            LocalDate dateSignature,
            LocalDate dateDebut,
            LocalDate dateFin) {

        // 1. Vérifier les dates
        if (dateSignature == null || dateDebut == null
                || dateFin == null || dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Dates invalides");
        }

        // 2. Rechercher la réservation
        List<Reservation> reservations = reservationRepository
                .findByDateDebutAndDateFin(dateDebut, dateFin);

        if (reservations.size() != 1) {
            throw new IllegalArgumentException(
                    "Aucune réservation unique trouvée pour ces dates");
        }

        Reservation reservation = reservations.get(0);

        if (reservation.getContrat() != null) {
            throw new IllegalArgumentException(
                    "Cette réservation possède déjà un contrat");
        }

        if (reservation.getVehicule() == null) {
            throw new IllegalArgumentException(
                    "Aucun véhicule associé à la réservation");
        }

        // 3. Calculer le montant
        long jours = ChronoUnit.DAYS.between(
                dateDebut, dateFin) + 1;

        BigDecimal montant = reservation.getVehicule()
                .getTarifJournalier()
                .multiply(BigDecimal.valueOf(jours));

        // 4. Créer le contrat
        Contrat contrat = new Contrat();
        contrat.setDateSignature(dateSignature);
        contrat.setMontantTotal(montant);
        contrat.setValide(false);

        // 5. Enregistrer le contrat
        Contrat savedContrat = contratRepository.save(contrat);

        // 6. Affecter à la réservation
        reservation.setContrat(savedContrat);
        reservationRepository.save(reservation);

        // 7. Retourner le DTO
        return contratMapper.toContratDTO(savedContrat);
    }

}
