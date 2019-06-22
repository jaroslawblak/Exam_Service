package com.gucwa.project.examService.client.services;

import config.ExamFactory;
import model.Exam;
import model.Question;
import model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

@Component
public class MenuInformant {
    public static final String WELCOME_MESSAGE = "Welcome in exam service." +
            "\n Press [1] to enter your student data." +
            "\n Press [2] to generate exam for you." +
            "\n Press [3] to show history and best results." +
            "\n Press [0] to exit program..." +
            "\n Waiting...";

    private User user;
    private ExamRanking examRanking;
    private ExamService examService;

    public MenuInformant() {
        this.user = null;
        this.examRanking = new ExamRanking();
        this.examService = new ExamService(new RestTemplate());
        while (true) {
            this.executeProgram();
        }
    }

    public void executeProgram() {
        System.out.println(WELCOME_MESSAGE);
        Scanner sc = new Scanner(System.in);
        int selectedOption = sc.nextInt();
        sc.nextLine();

        switch (selectedOption) {
            case 0:
                System.exit(-1);
                break;
            case 1:
                boolean flag = true;
                while (flag) {
                    System.out.println("Enter your first name:");
                    String firstName = sc.nextLine();
                    System.out.println("Enter your last name:");
                    String lastName = sc.nextLine();
                    System.out.println("Enter your album ID:");
                    long albumID = sc.nextLong();
                    sc.nextLine();
                    String response;
                    do {
                        System.out.println("Do you want to accept your data? ([y] - YES, [n] - NO");
                        response = sc.nextLine();
                        if (response.equalsIgnoreCase("Y")) {
                            if (!firstName.equalsIgnoreCase("") &&
                                    !lastName.equalsIgnoreCase("") &&
                                    albumID != 0) {
                                user = new User(firstName, lastName);
                                user.setAlbumID(albumID);
                                flag = false;
                                break;
                            } else {
                                System.out.println("Your data can not be empty!");
                                break;
                            }
                        } else if (response.equalsIgnoreCase("N")) {
                            break;
                        }
                    } while (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n"));
                }
                System.out.println("Generating exam...");
            case 2:
                if (user == null) {
                    System.out.println("Enter your data before test!");
                    break;
                }
                Exam exam = examService.getExam();
                Set<Question> questions = exam.getQuestions();
                Map<String, Integer> inputAnswers = new LinkedHashMap<>();
                for (Question question : questions) {
                    System.out.println(question.getTopic());
                    for (Map.Entry answer : question.getAvailableAnswers().entrySet()) {
                        System.out.println(answer.getKey() + "-" + answer.getValue());
                    }
                    inputAnswers.put(question.getTopic(), new Scanner(System.in).nextInt());// do mapy sie wklada, do listy sie dodaje xdd
                }

                ExamFactory examFactory = new ExamFactory();
                Exam verifiedExam = examService.veryfyExam(examFactory.generateExamWithAnswers(questions, inputAnswers, user));
                System.out.println("Your score is " + verifiedExam.getNote() + " %");
                examRanking.getRanking().put(user.getFirstName() + " " + user.getLastName(), verifiedExam.getNote());
                break;
            case 3:
                System.out.println("Enter password to entry ranking:");
                String password = sc.nextLine();
                if (examService.veryfyUser(password)) {
                    System.out.println("Access granted!");
                } else {
                    System.out.println("Access denied!");
                    break;
                }
                System.out.println("\n Press [1] to show ranking." +
                        "\n Press [2] to show user." +
                        "\n Press [3] to modyfy note for user." +
                        "\n Press [0] to back to main menu...");
                switch (sc.nextInt()) {
                    case 1:
                        System.out.println(examRanking.showRanking());
                        break;
                    case 2:
                        sc.nextLine();
                        System.out.println("Enter first and last name of user");
                        String userToSearch = sc.nextLine();
                        System.out.println("User was found. His/Her score is: \n" + examRanking.searchResultForUser(userToSearch));
                        break;
                    case 3:
                        sc.nextLine();
                        System.out.println("Enter user name");
                        String userName = sc.nextLine();
                        if (!examRanking.getRanking().containsKey(userName)) {
                            System.out.println("No user in history!");
                        } else {
                            System.out.println("Enter new note for user");
                            Double newNote = sc.nextDouble();
                            examRanking.getRanking().put(userName, newNote);
                            System.out.println("Updated...");
                        }
                        break;
                    case 0:
                        break;
                }
                break;
        }
    }
}
