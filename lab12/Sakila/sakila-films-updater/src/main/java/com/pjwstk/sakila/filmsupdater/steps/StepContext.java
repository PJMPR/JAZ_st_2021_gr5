package com.pjwstk.sakila.filmsupdater.steps;

import com.pjwstk.sakila.data.model.Language;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonCast;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class StepContext {

    private List<PersonCast> cast;
    private List<String> languages;
    private List<String> categories;
    private DateRange dateRange;
    private List<Language> dbLanguages = new ArrayList<Language>();
    List<MovieDb> moviesFromService;

    @RequiredArgsConstructor
    @Getter
    @Setter
    public class DateRange{
        private final Date start;
        private final Date end;
    }
}
