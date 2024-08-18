package home.ahmad.restHALbrowser.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.transaction.annotation.Transactional;

import home.ahmad.restHALbrowser.model.Passport;

/**
 * 
 * @author Ahmad Alrefai
 */
@Transactional
@RepositoryRestResource(collectionResourceRel = "passport", path = "primefaces")
public interface PassportRepository extends JpaRepository<Passport, Long> {

    // diese Methode einen Optional<Passport> zurückgibt, kann der Aufrufer sicher prüfen, 
	// ob ein Ergebnis vorhanden ist, bevor auf das Passobjekt zugegriffen wird.
    Optional<Passport> findByPassportNo(String passportNo);

    /*
     * Diese Methode sucht nach allen Pässen, die nach einem bestimmten Ausstellungsdatum ausgestellt wurden.
     */
    List<Passport> findByIssueDateAfter(@Param("after") @DateTimeFormat(iso = ISO.DATE) LocalDate date);

    // Find all passports issued between two dates
    List<Passport> findByIssueDateBetween(@Param("after") @DateTimeFormat(iso = ISO.DATE) LocalDate after, 
                                          @Param("before") @DateTimeFormat(iso = ISO.DATE) LocalDate before);

    /*
     * Die Verwendung von Containing ermöglicht es, eine Teilzeichenfolgen-Suche durchzuführen,
     *  sodass beispielsweise eine Suche nach "Ger" auch "Germany" finden würde.
     */
    List<Passport> findByCountryOfIssueContaining(@Param("country") String country);
}

