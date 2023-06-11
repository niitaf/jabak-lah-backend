package org.spring.jabaklah.repository;

import org.spring.jabaklah.entity.Backoffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BackofficeRepository extends JpaRepository<Backoffice,Long> {
    @Query(value="SELECT backoffice FROM Backoffice backoffice WHERE backoffice.backofficeUser.username=:username")
    Backoffice findBackOfficeByIdentifiant (@Param(value = "username") String username);

    //Backoffice findByBackofficeUser(String usename);
    @Query(value="SELECT b FROM Backoffice b  WHERE b.backofficeUser.username=:username")
    Backoffice findAgentByIdentifiant (@Param(value = "username") String username);
}
