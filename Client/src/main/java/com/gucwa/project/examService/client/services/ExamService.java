package com.gucwa.project.examService.client.services;

import model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service //ta kalsa bedzie pełnić funkcje service
public class ExamService {
    private RestTemplate restTemplate; // sluzy do komunikacji, moze tworzyc porty do nasluchiwania
//konstruktor
    public ExamService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //metoda klienta, uzywasz resta zeby pobrac obiekt z url, klase jakiego typu obiekt zwraca ten port
    public Exam getExam(){
        return  restTemplate.getForObject("http://localhost:8080/exam", Exam.class);
    }
    // teraz robimy post, wiec ten co odbieram i ten co wysylam musi byc
    public Exam veryfyExam(Exam exam){
        return restTemplate.postForObject("http://localhost:8080/veryfyexam", Exam.class, Exam.class);
    }
}
