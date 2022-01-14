package com.pjwstk.sakila.filmsupdater.steps;

import com.pjwstk.sakila.data.repositories.FilmRepository;
import com.pjwstk.sakila.data.repositories.IDbContext;
import com.pjwstk.sakila.data.repositories.LanguageRepository;
import info.movito.themoviedbapi.TmdbApi;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class ProcessStepBase implements ProcessStep{

    protected final TmdbApi moviesClient;
    protected final IDbContext dbContext;

    @Setter
    protected StepContext context;
    protected ProcessStep nextStep;

    @Override
    public abstract boolean canExecute();

    @Override
    public void execute() {
        if(canExecute()){
            doAction();
        }
        if(nextStep!=null)
            nextStep.execute();
    }

    public void breakProcess(){
        nextStep=null;
    }
    protected abstract void doAction();
}
