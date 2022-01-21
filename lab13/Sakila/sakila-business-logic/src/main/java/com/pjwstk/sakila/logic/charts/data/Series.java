package com.pjwstk.sakila.logic.charts.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Series {
    private String title;
    private List<SeriesValue> values;
}


