package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ranking {
    private List<customerSpentMoney> ranking = new ArrayList<>();
    private List<customerWatchedMovies> rankingMovies = new ArrayList<>();
    private List<rentMoviesByMonth> rentMoviesByMonths = new ArrayList<>();

    public ranking() {
    }

    public ranking(List<customerSpentMoney> ranking, List<customerWatchedMovies> rankingMovies, List<rentMoviesByMonth> rentMoviesByMonth) {
        this.ranking = ranking;
        this.rankingMovies = rankingMovies;
        this.rentMoviesByMonths = rentMoviesByMonth;
    }

    public List<customerWatchedMovies> getRankingMovies() {
        return rankingMovies;
    }

    public void setRankingMovies(List<customerWatchedMovies> rankingMovies) {
        this.rankingMovies = rankingMovies;
    }

    public List<customerSpentMoney> getRanking() {
        return ranking;
    }

    public void setRanking(List<customerSpentMoney> ranking) {
        this.ranking = ranking;
    }

    public List<rentMoviesByMonth> getRentMoviesByMonths() {
        return rentMoviesByMonths;
    }

    public void setRentMoviesByMonths(List<rentMoviesByMonth> rentMoviesByMonths) {
        this.rentMoviesByMonths = rentMoviesByMonths;
    }
}
