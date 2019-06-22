package com.gucwa.project.examService.server.services;

import model.Exam;
import model.Question;

import java.util.Set;

public class ExamChecker {

    public Exam checkExam(Exam exam) {
        double counter = 0;
        Set<Question> questions = exam.getQuestions();
        for (Question question : questions) {
            String answer = exam.getUserAnswers().get(question.getTopic()).toString();
            if (answer.compareTo(question.getCorrectAnswer()) == 0) {
                counter++;
            }
        }
        double result = counter / exam.getQuestions().size() * 100;
        exam.setNote(result);
        return exam;
    }
}
