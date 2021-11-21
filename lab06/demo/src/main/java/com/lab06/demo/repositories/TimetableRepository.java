package com.lab06.demo.repositories;

import com.lab06.demo.entities.Timetable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Long> {
    @Query("select t from Timetable t where t.calculation.id = ?1")
    List<Timetable> findAllById(long id);
}
