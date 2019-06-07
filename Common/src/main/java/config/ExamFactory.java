package config;

import model.Exam;
import model.Question;
import model.User;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ExamFactory {

    public ExamFactory() {
    }

    public Exam getExam(int questionsAmount, User user){
        Exam exam = new Exam();
        exam.setUser(user);
        exam.setQuestions(this.loadQuestions(questionsAmount));
        return exam;
    }

    private  Set<Question> loadQuestions(int questionsAmount)   {
        Set<Question> questions= new LinkedHashSet<>();
        Map<Integer, String> answers= new LinkedHashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Objects.requireNonNull(new ClassPathResource("questions.csv").getFile())))){
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                String data[] = stringBuilder.toString().split(";");
                for (int i=1; i<questionsAmount; i++){
                    answers.put(i,data[i]);
                }
                questions.add(new Question(data[0],
                        data[5],
                        new LinkedHashMap<>(answers)
                        ));
                stringBuilder.delete(0,stringBuilder.length());
                answers.clear();
                line = bufferedReader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
            e.getMessage();
        }
        return questions;
    }
}
