package com.pjwstk.sakila.data.repositories;

import com.pjwstk.sakila.data.model.FilmActor;
import com.pjwstk.sakila.data.model.FilmActorPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorPK> {
}
