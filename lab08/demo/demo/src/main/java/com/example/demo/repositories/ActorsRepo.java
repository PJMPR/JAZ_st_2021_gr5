package com.example.demo.repositories;

import com.example.demo.model.Actor;
import com.example.demo.repositories.projections.IActors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorsRepo extends JpaRepository<Actor, Integer> {
    @Query(value = "select a from Actor a")
    List<IActors> getAllActors();
}
