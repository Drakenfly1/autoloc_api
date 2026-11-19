
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Vehicule;
import tn.esprit.autoloc_api.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc_api.web.dto.VehiculeResponseDTO;

@Component
public class VehiculeMapper {

    public VehiculeResponseDTO toDTO(Vehicule vehicule) {

        Long idAgence = vehicule.getAgence() != null
                ? vehicule.getAgence().getIdAgence()
                : null;

        return new VehiculeResponseDTO(
                vehicule.getIdVehicule(),
                vehicule.getImmatriculation(),
                vehicule.getMarque(),
                vehicule.getModele(),
                vehicule.getCategorie(),
                vehicule.getTarifJournalier(),
                vehicule.getStatut(),
                idAgence
        );
    }


    public VehiculeResponseDTO toVehiculeDTO(Vehicule vehicule) {
        VehiculeResponseDTO dto = toDTO(vehicule);

        return new VehiculeResponseDTO(
                dto.idVehicule(),
                dto.immatriculation(),
                dto.marque(),
                dto.modele(),
                dto.categorie(),
                dto.tarifJournalier(),
                dto.statut(),
                dto.idAgence()
        );
    }


    public Vehicule toEntity(VehiculeRequestDTO dto) {

        Vehicule vehicule = new Vehicule();

        vehicule.setImmatriculation(dto.immatriculation());
        vehicule.setMarque(dto.marque());
        vehicule.setModele(dto.modele());
        vehicule.setCategorie(dto.categorie());
        vehicule.setTarifJournalier(dto.tarifJournalier());
        vehicule.setStatut(dto.statut());

        return vehicule;
    }
}
