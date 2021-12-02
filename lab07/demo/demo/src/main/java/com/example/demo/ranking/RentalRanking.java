package com.example.demo.ranking;
import com.example.demo.data.Rental;
import java.util.List;

public class RentalRanking {
    private final List<RentalData> rankedRental;

    public void addToRentalRanking(int month, int rentMovies) {
        rankedRental.add(new RentalData(month, rentMovies));
    }

    public RentalRanking(List<RentalData> rankedRental) {
        this.rankedRental = rankedRental;
    }

    public List<RentalData> getRankedRentalList() {
        return rankedRental;
    }
}
