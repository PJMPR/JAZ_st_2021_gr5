package com.lab06.demo.services;

import com.lab06.demo.entities.Timetable;
import com.lab06.demo.helper.CSVHelper;
import com.lab06.demo.repositories.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class CSVService {
    TimetableRepository timetableRepository;

    @Autowired
    public CSVService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public CSVService() {
    }

    public ByteArrayInputStream load(long id) {
        List<Timetable> timetableList = timetableRepository.findAllById(id);

        return CSVHelper.toCSV(timetableList);
    }
}
