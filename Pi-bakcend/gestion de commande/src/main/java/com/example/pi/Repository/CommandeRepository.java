package com.example.pi.Repository;

import com.example.pi.entities.Commande;
import com.example.pi.entities.Countstatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query(value = "select * from commande order by date_commande desc ", nativeQuery = true)
    public List<Commande> getAllcommandsbydatecommande();

    //Page<Commande> findByUseEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);


    //@Query(value = "select new com.example.pi.entities.countstatus (SELECT COUNT(*), status FROM commande GROUP BY status)")
    //public List<Countstatus> getstatusbycommand();



}
