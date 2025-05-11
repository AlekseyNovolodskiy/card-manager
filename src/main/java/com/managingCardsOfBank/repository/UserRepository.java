package com.managingCardsOfBank.repository;

import com.managingCardsOfBank.entity.UserEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntityInfo, Long> {

    Optional<UserEntityInfo> findUserEntitiesByEmail(String email);

    @Query(value = "select u from UserEntityInfo u where u.email = :email" )
    Optional<UserEntityInfo> findByEmail (String email);

    boolean existsByEmail(String email);
}
