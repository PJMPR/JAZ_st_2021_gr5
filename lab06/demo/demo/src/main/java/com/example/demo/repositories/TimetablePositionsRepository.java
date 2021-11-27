package com.example.demo.repositories;

import com.example.demo.model.TimetablePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetablePositionsRepository extends JpaRepository<TimetablePosition, Integer> {
}
