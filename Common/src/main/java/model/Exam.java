package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exam {
    private User user;
    private Set<Question> questions;
    private Double note;
    private Map<String, Integer> userAnswers;

    public Exam() {
        this.note = 0.0;
    }

    public Exam(User user) {
        this.user = user;
        this.note = 0.0;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Map<String, Integer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Map<String, Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Exam user(final User user) {
        this.user = user;
        return this;
    }

    public Exam questions(final Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Exam note(final double note) {
        this.note = note;
        return this;
    }


}
