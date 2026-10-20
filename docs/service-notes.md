# Atelier 4 — Note sur la couche Service, IoC et injection de dépendances

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut |
|---|---|---|
| Champ `final` possible | Oui | Non |
| Dépendance visible dans la signature de la classe | Oui | Non |
| Utilisation avec `new` hors du conteneur Spring | Oui, à condition de fournir explicitement la dépendance au constructeur | Risquée : la dépendance reste `null` sans intervention de Spring |
| Objet toujours complètement initialisé | Oui | Non |
| Dépendances explicites | Oui | Non, elles sont cachées dans les attributs |
| Signalement SonarQube for IDE | Non pour cette pratique | Oui, règle S6813 |
| Mode retenu dans AutoLoc | Oui | Non |

### Conclusion

L'injection par constructeur est utilisée dans le projet AutoLoc.

Avec Lombok, l'annotation `@RequiredArgsConstructor` génère automatiquement un
constructeur contenant les champs `final`.

Par exemple :

```java
@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
}