package tn.esprit.autoloc_api.service;


import tn.esprit.autoloc_api.domain.Client;

import java.util.List;

public interface IClientService {
    Client create(Client client);
    Client findById(Long id);
    List<Client> findAll();
    Client update(Long id, Client client);
    void deleteById(Long id);
}
