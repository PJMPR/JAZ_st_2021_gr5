package com.example.demo.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasjsChartData {

    static Map<Object,Object> map = null;
    static List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
    static List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();

    static {
        map = new HashMap<Object,Object>(); map.put("label", "18 yrs and Under"); map.put("y", 7);dataPoints1.add(map);
        map = new HashMap<Object,Object>(); map.put("label", "19 to 32 yrs"); map.put("y", 51);dataPoints1.add(map);
        map = new HashMap<Object,Object>(); map.put("label", "32 to 45 yrs"); map.put("y", 12);dataPoints1.add(map);
        map = new HashMap<Object,Object>(); map.put("label", "45 to 60 yrs"); map.put("y", 17);dataPoints1.add(map);
        map = new HashMap<Object,Object>(); map.put("label", "60 yrs and over"); map.put("y", 13);dataPoints1.add(map);

        list.add(dataPoints1);
    }

    public static List<List<Map<Object, Object>>> getCanvasjsDataList() {
        return list;
    }
}
