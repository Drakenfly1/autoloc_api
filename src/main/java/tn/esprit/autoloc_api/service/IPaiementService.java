package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.domain.Paiement;
import java.util.List;

public interface IPaiementService {

    Paiement findById(Long id);

    List<Paiement> findAll();
}