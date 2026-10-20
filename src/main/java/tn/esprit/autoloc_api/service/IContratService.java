package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat create(Contrat contrat);
    Contrat findById(Long id);
    List<Contrat> findAll();
    Contrat update(Long id, Contrat contrat);
    void deleteById(Long id);
}
