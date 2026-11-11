
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Client;
import tn.esprit.autoloc_api.web.dto.ClientRequestDTO;
import tn.esprit.autoloc_api.web.dto.ClientResponseDTO;

@Component
public class ClientMapper {

    // Entité vers DTO
    public ClientResponseDTO toDTO(Client client) {

        return new ClientResponseDTO(
                client.getIdClient(),
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.getNumPermis(),
                client.getDateInscription()
        );
    }

    // DTO vers Entité
    public Client toEntity(ClientRequestDTO dto) {

        Client client = new Client();

        client.setNom(dto.nom());
        client.setPrenom(dto.prenom());
        client.setEmail(dto.email());
        client.setTelephone(dto.telephone());
        client.setNumPermis(dto.numPermis());
        client.setDateInscription(dto.dateInscription());

        return client;
    }
}
