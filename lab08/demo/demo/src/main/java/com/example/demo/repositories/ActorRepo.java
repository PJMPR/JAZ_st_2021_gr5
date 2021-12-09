package com.example.demo.repositories;
import com.example.demo.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Integer> {
    @Query("SELECT '*' FROM Actor a WHERE a.firstName = :firstName AND a.lastName = :lastName")
    Collection<Actor> getActorsByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT a.firstName, a.lastName FROM Actor a")
    Collection<Actor> getActors();
}
