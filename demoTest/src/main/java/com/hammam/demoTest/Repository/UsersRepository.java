package com.hammam.demoTest.Repository;

import com.hammam.demoTest.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    // JPQL query to retrieve a Users entity based on the tckn value provided as a parameter.
    @Query("SELECT s FROM Users s WHERE s.TCKN = ?1")
    Optional<Users> findUsersByTCKN(Long TCKN);

    // JPQL query to delete a Users entity based on the tckn value provided as a parameter.
    @Modifying
    @Query("DELETE FROM Users s WHERE s.TCKN = ?1")
    void deleteByTCKN(Long TCKN);

    //
}
