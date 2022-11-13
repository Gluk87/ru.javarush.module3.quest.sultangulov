package ru.javarush.quest.repository;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.Quest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
public class QuestRepository {
    private Quest quest;

    public QuestRepository() throws IOException {
        loadJsonFile();
    }

    public String getQuestionTextById(int id) {
        return quest.getQuestions().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get()
                .getText();
    }

    public boolean isLastQuestionById(int id) {
        return quest.getQuestions().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get()
                .isLastQuestion();
    }

    public String isWrongAnswer (int id) {
        return quest.getQuestions().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get()
                .getText();
    }

    public List<Answer> getAnswersByQuestionId(int id) {
        return quest.getQuestions().get(id).getAnswers();
    }
    private void loadJsonFile() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("quest.json").getFile());
        quest = new JsonMapper().readValue(file, Quest.class);
    }
}
