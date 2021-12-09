package com.example.demo.updater;

import com.example.demo.contract.LanguagesDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import com.example.demo.repositories.FilmRepo;
import com.example.demo.repositories.LanguageRepo;
import com.example.demo.repositories.projections.IFilm;
import com.example.demo.repositories.projections.ILanguages;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilmUpdate implements Chain {
    private final Logger logger = Logger.getLogger(FilmUpdate.class.getName());

    private Chain nextInChain;
    private FilmRepo repo;
    private LanguageRepo langRepo;

    public FilmUpdate(FilmRepo repo, LanguageRepo langRepo) {
        this.repo = repo;
        this.langRepo = langRepo;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void query(MovieDto movieDto, OMDbDto omDbDto) {
        List<String> dbList = repo.getAllFilms().stream().map(IFilm::getTitle).collect(Collectors.toList());
        List<String> dbLangList = langRepo.getAllLanguages().stream().map(ILanguages::getName).collect(Collectors.toList());

        if (!dbList.contains(movieDto.getTitle())) {
            long time = new Date().getTime();

            List<LanguagesDto> getOrgLang = movieDto.getLanguages()
                    .stream()
                    .filter(l -> l.getIso_639_1().equals(movieDto.getOriginal_language()))
                    .collect(Collectors.toList());

            String lang;

            if (getOrgLang.size() != 0) {
                lang = getOrgLang.get(0).getEnglish_name();
            } else {
                lang = "English";
            }

            Language l = new Language();
            l.setLanguageId(dbLangList.indexOf(lang) + 1);
            l.setName(lang);

            Film f = new Film();
            f.setFilmId(dbList.size() + 1);
            f.setTitle(movieDto.getTitle());
            f.setDescription(movieDto.getDescription());
            f.setReleaseYear(Integer.parseInt(movieDto.getRelease_date().split("-")[0]));
            f.setLanguage(l);
            f.setLength(movieDto.getRuntime());

            if (!omDbDto.getRating().equals("Not Rated") && !omDbDto.getRating().equals("N/A"))
                f.setRating(omDbDto.getRating());

            f.setLastUpdate(new Timestamp(time));

            repo.save(f);
            logger.log(Level.INFO, "Found and added new film: " + f.getTitle());
        }
        nextInChain.query(movieDto, omDbDto);
    }
}
