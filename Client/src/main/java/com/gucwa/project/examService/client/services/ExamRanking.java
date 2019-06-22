package com.gucwa.project.examService.client.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ExamRanking {


    private HashMap<String, Double> ranking;

    public ExamRanking() {
        ranking = new HashMap<>();
    }


    public HashMap<String, Double> getRanking() {
        return ranking;
    }

    public String showRanking() {
        Map<String, Double> sortedMap = this.getRanking()
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        StringBuilder result = new StringBuilder("Actual ranking \n");
        for (Map.Entry<String, Double> record : sortedMap.entrySet()) {
            result.append(record.getKey()).append(" ").append(record.getValue()).append("%").append("\n");
        }
        return result.toString();
    }

    public String searchResultForUser(String userName) {
        return this.ranking.containsKey(userName) ? (ranking.get(userName) + "%") : ("User didn't take the exam");
    }
}
