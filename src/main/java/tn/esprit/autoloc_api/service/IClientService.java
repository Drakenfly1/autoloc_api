package tn.esprit.autoloc_api.service;


import tn.esprit.autoloc_api.domain.Client;

import java.util.List;

public interface IClientService {
    List<Client> retrieveAllClients();
    Client addClient(Client c);
    Client updateClient(Client c);
    Client retrieveClient(Long idClient);
    void removeClient(Long idClient);
    List<Client> addClients (List<Client> clients);
}
