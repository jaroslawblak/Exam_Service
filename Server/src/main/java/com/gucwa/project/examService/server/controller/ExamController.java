package com.gucwa.project.examService.server.controller;

import com.gucwa.project.examService.server.services.ExamChecker;
import config.ExamFactory;
import model.Exam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // oznaczenie ze klasa pełni funkcję restkontrolera (klasa zawierajaca wszystkie porty (nasluchanie))
public class ExamController {

    @Value("${exam.questions.amount}")
    private int questionAmount;

    @Value("${exam.ranking..password}")
    private String password;

    @GetMapping("/exams") //na jakim url nasluchuje
    public ResponseEntity<Exam> Exam() {
        return new ResponseEntity<>(new ExamFactory().getExam(this.questionAmount, null), HttpStatus.OK);
    }

    @PostMapping("/exams")
    public ResponseEntity<Exam> checkExam(@RequestBody Exam exam) {
        ExamChecker examChecker = new ExamChecker();
        return new ResponseEntity<>(examChecker.checkExam(exam), HttpStatus.OK);
    }

    @PostMapping("/admins/password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody String userPassword) {
        if (password.equals(userPassword)) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }
    }
}
