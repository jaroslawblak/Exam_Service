package com.gucwa.project.examService.server.controller;

import com.gucwa.project.examService.server.services.ExamChecker;
import config.ExamFactory;
import model.Exam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // oznaczenie ze klasa pełni funkcję restkontrolera (klasa zawierajaca wszystkie porty (nasluchanie))
public class ExamController {

    @Value("${exam.questions.amount}")
    private int questionAmount;

    @RequestMapping("/exam") //na jakim url nasluchuje
    public Exam Exam(){

        return new ExamFactory().getExam(this.questionAmount, null);
    }

    @RequestMapping("/veryfyexam")
    public Exam checkExam(@RequestBody Exam exam){

        ExamChecker examChecker = new ExamChecker(exam);
        return examChecker.checkExam();
    }
}
