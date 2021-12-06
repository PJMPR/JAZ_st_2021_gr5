package com.example.demo.updater;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.model.Language;
import com.example.demo.repositories.LanguageRepo;
import com.example.demo.repositories.projections.ILanguages;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageUpdate implements Chain {
    private Chain nextInChain;
    private LanguageRepo repo;

    public LanguageUpdate(LanguageRepo repo) {
        this.repo = repo;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void query(MovieDto movieDto, OMDbDto omDbDto) {

        List<String> langList = Arrays.stream(omDbDto.getLanguages().split(", ")).toList();


        for (String lang : langList) {
            List<String> dbList = repo.getAllLanguages().stream().map(ILanguages::getName).collect(Collectors.toList());

            if (!dbList.contains(lang)) {
                long time = new Date().getTime();

                Language l = new Language();
                l.setLanguageId(dbList.size() + 1);
                l.setName(lang);
                l.setLastUpdate(new Timestamp(time));

                repo.save(l);
            }
            nextInChain.query(movieDto, omDbDto);
        }
    }
}