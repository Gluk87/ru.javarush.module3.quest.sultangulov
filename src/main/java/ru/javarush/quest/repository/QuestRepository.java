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
    private final Quest quest;

    public QuestRepository(Quest quest) {
        this.quest = quest;
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

    public List<Answer> getAnswersByQuestionId(int id) {
        return quest.getQuestions().get(id).getAnswers();
    }
}
