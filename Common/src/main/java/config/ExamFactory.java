package config;

import model.Exam;
import model.Question;
import model.User;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ExamFactory {

    public ExamFactory() {
        //jackson requirements
    }

    // metoda get exam
    public Exam getExam(int questionsAmount, User user) {
        Exam exam = new Exam();
        exam.setUser(user);
        exam.setQuestions(this.loadQuestions(questionsAmount));
        return exam;
    }

    // ona zczytuje z pliku question.csv
    private Set<Question> loadQuestions(int questionsAmount) {
        Set<Question> questions = new LinkedHashSet<>(); //zbior pytan bez powtorzenia elementow, zachowuje kolejnosc
        Map<Integer, String> answers = new LinkedHashMap<>(); // lista, przechowujaca pary
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Objects.requireNonNull(new ClassPathResource("questions.csv").getFile())))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                String data[] = stringBuilder.toString().split(";"); // tablica stringow, rozdzielona srednikami
                for (int i = 1; i < 5; i++) {
                    answers.put(i, data[i]);
                }
                questions.add(new Question(data[0],
                        data[5],
                        new LinkedHashMap<>(answers)
                ));
                stringBuilder.delete(0, stringBuilder.length());
                answers.clear();
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return this.chooseRandomQuestions(questions, questionsAmount);
    }

    public Exam generateExamWithAnswers(Set<Question> questions, Map<String, Integer> answers, User user) {
        Exam exam = new Exam(user);
        exam.setUserAnswers(answers);
        exam.setQuestions(questions);
        return exam;
    }

    public Set<Question> chooseRandomQuestions(Set<Question> questions, int questionsAmount){
        LinkedHashSet<Question> filteredQuestions = new LinkedHashSet<>();
        int rand;
        if(questions.size() < questionsAmount){
            System.out.println("There is not enough questions to choose, change the number of questions in configuration file "+
                                "\n or add new questions \n Now you have: " + questions.size() + " questions, and " + questionsAmount + " to choose");
            return Collections.emptySet();
        }
        while(filteredQuestions.size() < questionsAmount){
            int index = 0;
            rand = new Random().nextInt(questions.size());
            for (Question question : questions) {
                if(index == rand){
                    filteredQuestions.add(question);
                }
                index++;
            }

        }
        return filteredQuestions;
    }
}
