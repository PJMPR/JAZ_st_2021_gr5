package com.example.demo.repositories;
import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ActorInsertRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertActor(Actor actor) {
        entityManager.createNativeQuery("INSERT INTO actor(first_name, last_name) VALUES (?,?)")
                .setParameter(1, actor.getFirstName())
                .setParameter(2, actor.getLastName())
                .executeUpdate();
    }

    @Transactional
    public void deleteFilmActor(Actor actor, Film film) {
        entityManager.createNativeQuery("DELETE FROM film_actor WHERE film_actor.actor_id = ? AND film_actor.film_id = ?")
                .setParameter(1, actor.getActorId())
                .setParameter(2, film.getFilmId())
                .executeUpdate();
    }

    @Transactional
    public void deleteActor(Actor actor) {
        entityManager.createNativeQuery("DELETE FROM actor WHERE actor.first_name = ? AND actor.last_name = ?")
                .setParameter(1, actor.getFirstName())
                .setParameter(2, actor.getLastName())
                .executeUpdate();
    }

    @Transactional
    public void insertFilmActor(Actor actor, Film film) {
        entityManager.createNativeQuery("INSERT INTO film_actor(actor_id, film_id) VALUES (?,?)")
                .setParameter(1, actor.getActorId())
                .setParameter(2, film.getFilmId())
                .executeUpdate();
    }
}
