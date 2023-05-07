package tn.esprit.pibakcend.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import tn.esprit.pibakcend.entities.Evenement;
import tn.esprit.pibakcend.entities.User;

import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement,Integer> , PagingAndSortingRepository<Evenement, Integer> {

    @Query("SELECT eve.Lieu, count(eve.idEvenement) "
            + "FROM Evenement eve "
            + "WHERE eve.idEvenement = ?1 "
            + "GROUP BY eve.Lieu")
    List<Object[]>  getEventsCountbyId(Integer id);
    @Query("SELECT eve "
            + "FROM Evenement eve "
            + "WHERE eve.idEvenement = ?1 ")
    List<Evenement>  getEvenementById(Long id);

    @Query("SELECT E FROM Evenement E WHERE "
            + "lower(E.Lieu) LIKE lower(concat('%', ?1,'%')) "
            + "OR lower(E.titreEvennement) LIKE lower(concat('%', ?1,'%')) ")
    Page<Evenement> filterEvenementList(String filter, Pageable pageable);

    @Query("SELECT count(E.idEvenement) FROM Evenement E")
    Long retrieveEvenementCount();

    List<Evenement> findAllByUserr(User user);

}
