
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
}
