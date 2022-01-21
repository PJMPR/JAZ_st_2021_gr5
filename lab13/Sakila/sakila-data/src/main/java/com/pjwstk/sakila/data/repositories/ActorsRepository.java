package com.pjwstk.sakila.data.repositories;

import com.pjwstk.sakila.data.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorsRepository extends JpaRepository<Actor, Long> {
}
