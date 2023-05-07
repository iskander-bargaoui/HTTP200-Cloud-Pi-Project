package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.*;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    Profile findByUsername(String username);
    List<Profile> findByCategorie(Categorie categorie );

    List<Profile> findByIsVerified(boolean isVerified);
    List<Profile> findAllByUser(User userrr);


}
