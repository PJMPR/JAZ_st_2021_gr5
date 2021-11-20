package com.lab06.demo.repositories;

import com.lab06.demo.timetable.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
