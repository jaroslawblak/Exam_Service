package com.gucwa.project.examService.client.services;

import model.Exam;
import model.Question;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class MenuInformant {
    public static final String WELCOME_MESSAGE = "Welcome in exam service." +
            "\n Press [1] to generate exam for you." +
            "\n Press [2] to show history and best results." +
            "\n Press [0] to exit program..." +
            "\n Waiting...";

    public MenuInformant() {
        while (true) {
           this.executeProgram();
       }
    }

    public void executeProgram(){
        System.out.println(WELCOME_MESSAGE);
        Scanner sc = new Scanner(System.in);
        int selectedOption = sc.nextInt();
        sc.nextLine();
        switch (selectedOption) {
            case 0:
                System.exit(-1);
                break;
            case 1:
                ExamService examService = new ExamService(new RestTemplate());
                Exam exam = examService.getExam();
                Set<Question> questions = exam.getQuestions();
                List<Integer> inputAnswers = new ArrayList<>();
                for (Question question: questions) {
                    System.out.println(question.getTopic());
                    for (Map.Entry answer : question.getAvailableAnswers().entrySet()){
                        System.out.println(answer.getKey() + "-" + answer.getValue());
                    }
                    inputAnswers.add(new Scanner(System.in).nextInt());
                    sc.nextLine();
                }
                break;
            case 2:
                break;
        }
    }
}
