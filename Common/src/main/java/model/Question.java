package model;

import java.util.Map;

public class Question {

    private String topic;
    private String correctAnswer;
    private Map<Integer, String> availableAnswers;

    public Question() {
    }

    public Question(String topic, String correctAnswer, Map<Integer, String> availableAnswers) {
        this.topic = topic;
        this.correctAnswer = correctAnswer;
        this.availableAnswers = availableAnswers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Map<Integer, String> getAvailableAnswers() {
        return availableAnswers;
    }

    public void setAvailableAnswers(Map<Integer, String> availableAnswers) {
        this.availableAnswers = availableAnswers;
    }

    public Question topic(final String topic) {
        this.topic = topic;
        return this;
    }

    public Question correctAnswer(final String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public Question avaibleAnswers(final Map<Integer, String> avaibleAnswers) {
        this.availableAnswers = avaibleAnswers;
        return this;
    }


}
