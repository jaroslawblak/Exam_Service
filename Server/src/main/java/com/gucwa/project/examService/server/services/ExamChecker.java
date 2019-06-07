package com.gucwa.project.examService.server.services;

import model.Exam;
import model.Question;

import java.util.Set;

public class ExamChecker {

    private Exam exam;

    public ExamChecker(Exam exam) {
        this.exam = exam;
    }

    public Exam checkExam(){
        int counter = 0;
        Set<Question> questions = this.exam.getQuestions();
        for( Question question: questions) {
            String answer = this.exam.getUserAnswers().get(question.getTopic()).toString();
            if (answer.compareTo(question.getCorrectAnswer()) == 0) {
                counter++;
            }
        }
            double result = counter/ this.exam.getQuestions().size() * 100;
            this.exam.setNote(result);
            return this.exam;
    }


}
