package tn.esprit.pibakcend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pibakcend.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {
}
