
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc_api.domain.*;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.ReservationMapper;
import tn.esprit.autoloc_api.repository.*;
import tn.esprit.autoloc_api.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc_api.web.dto.ReservationResponseDTO;
import tn.esprit.autoloc_api.web.dto.ReservationWithContratRequestDTO;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final IContratRepository contratRepository;
    private final ReservationMapper reservationMapper;

    @Override
    @Transactional
    public ReservationResponseDTO create(ReservationRequestDTO dto) {

        verifierDates(dto);

        Reservation reservation = reservationMapper.toEntity(dto);

        reservation.setClient(trouverClient(dto.idClient()));
        reservation.setVehicule(trouverVehicule(dto.idVehicule()));
        reservation.setContrat(trouverContrat(dto.idContrat()));

        Reservation saved = reservationRepository.save(reservation);

        return reservationMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponseDTO findById(Long id) {

        Reservation reservation = rechercherReservation(id);

        return reservationMapper.toDTO(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> findAll() {

        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public ReservationResponseDTO update(
            Long id, ReservationRequestDTO dto) {

        Reservation existante = rechercherReservation(id);

        verifierDates(dto);

        existante.setDateDebut(dto.dateDebut());
        existante.setDateFin(dto.dateFin());
        existante.setStatut(dto.statut());

        existante.setClient(trouverClient(dto.idClient()));
        existante.setVehicule(trouverVehicule(dto.idVehicule()));

        Reservation updated = reservationRepository.save(existante);

        return reservationMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Réservation", id);
        }

        reservationRepository.deleteById(id);
    }

    private Reservation rechercherReservation(Long id) {

        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Réservation", id)
                );
    }

    private Client trouverClient(Long idClient) {

        if (idClient == null) {
            return null;
        }

        return clientRepository.findById(idClient)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", idClient)
                );
    }

    private Vehicule trouverVehicule(Long idVehicule) {

        if (idVehicule == null) {
            return null;
        }

        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Véhicule", idVehicule)
                );
    }

    private Contrat trouverContrat(Long idContrat) {

        if (idContrat == null) {
            return null;
        }

        return contratRepository.findById(idContrat)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", idContrat)
                );
    }

    private void verifierDates(ReservationRequestDTO dto) {

        if (dto.dateDebut() == null || dto.dateFin() == null) {
            throw new IllegalArgumentException(
                    "Les dates de début et de fin sont obligatoires"
            );
        }

        if (dto.dateFin().isBefore(dto.dateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début"
            );
        }
    }

    @Override
    @Transactional
    public ReservationResponseDTO affecterReservationAClient(
            Long idReservation, String nom, String prenom) {

        // 1. Récupérer la réservation
        Reservation reservation = reservationRepository
                .findById(idReservation)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Réservation", idReservation));

        // 2. Rechercher le client
        List<Client> clients = clientRepository
                .findByNomIgnoreCaseAndPrenomIgnoreCase(nom, prenom);

        if (clients.size() != 1) {
            throw new IllegalArgumentException(
                    "Aucun client unique trouvé pour ce nom et prénom");
        }

        // 3. Affecter
        reservation.setClient(clients.get(0));

        // 4. Enregistrer
        Reservation saved = reservationRepository.save(reservation);

        // 5. Retourner un DTO
        return reservationMapper.toDTO(saved);
    }


    @Override
    @Transactional
    public ReservationResponseDTO creerReservationEtContratAssocie(
            ReservationWithContratRequestDTO requestDTO) {

        // 1. Vérifier les données
        if (requestDTO.dateDebut() == null
                || requestDTO.dateFin() == null
                || requestDTO.dateSignature() == null
                || requestDTO.statut() == null
                || requestDTO.dateFin().isBefore(requestDTO.dateDebut())) {
            throw new IllegalArgumentException(
                    "Données de réservation invalides");
        }

        // 2. Récupérer les entités existantes
        Client client = clientRepository
                .findById(requestDTO.idClient())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Client", requestDTO.idClient()));

        Vehicule vehicule = vehiculeRepository
                .findById(requestDTO.idVehicule())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Véhicule", requestDTO.idVehicule()));

        // 3. Calculer le montant du contrat
        long jours = ChronoUnit.DAYS.between(
                requestDTO.dateDebut(),
                requestDTO.dateFin()) + 1;

        BigDecimal montant = vehicule.getTarifJournalier()
                .multiply(BigDecimal.valueOf(jours));

        // 4. Créer et enregistrer le contrat
        Contrat contrat = new Contrat();
        contrat.setDateSignature(requestDTO.dateSignature());
        contrat.setMontantTotal(montant);
        contrat.setValide(false);

        Contrat savedContrat = contratRepository.save(contrat);

        // 5. Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setDateDebut(requestDTO.dateDebut());
        reservation.setDateFin(requestDTO.dateFin());
        reservation.setStatut(requestDTO.statut());

        // 6. Effectuer les affectations
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setContrat(savedContrat);

        // 7. Enregistrer la réservation
        Reservation saved = reservationRepository.save(reservation);

        // 8. Retourner le DTO
        return reservationMapper.toDTO(saved);
    }


}
