package com.example.demo.service;

import com.example.demo.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final EntityManager entityManager;
    private FilmsRepository filmsRepository;

    public void deleteFilm(int id) {
        entityManager.createQuery("DELETE from Film where filmId=:id").setParameter("id",id).executeUpdate();
    }
}