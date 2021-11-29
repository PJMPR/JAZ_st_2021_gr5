package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ranking {
    private List<customerSpentMoney> ranking = new ArrayList<>();

    public ranking() {}
    public ranking(List<customerSpentMoney> ranking) {
        this.ranking = ranking;
    }

    public List<customerSpentMoney> getRanking() {
        return ranking;
    }

    public void setRanking(List<customerSpentMoney> ranking) {
        this.ranking = ranking;
    }
}
