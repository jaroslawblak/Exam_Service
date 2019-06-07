package com.gucwa.project.examService.client.services;

import model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExamService {
    private RestTemplate restTemplate;

    public ExamService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Exam getExam(){
        return  restTemplate.getForObject("http://localhost:8080/exam", Exam.class);
    }
}
