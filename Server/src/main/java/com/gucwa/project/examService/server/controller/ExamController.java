package com.gucwa.project.examService.server.controller;

import config.ExamFactory;
import model.Exam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @Value("${exam.questions.amount}")
    private int questionAmount;

    @RequestMapping("/exam")
    public Exam Exam(){
        return new ExamFactory().getExam(this.questionAmount, null);
    }
}
