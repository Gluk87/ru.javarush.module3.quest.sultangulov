package ru.javarush.quest.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.service.QuestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {

    QuestService questService = new QuestService();
    QuestRepository questRepository = new QuestRepository(questService.getQuestFromFile("quest.json"));

    public QuestRepositoryTest() throws IOException {
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void checkGetQuestionTextById(int id) {
        String expectedText = "";
        String text = questRepository.getQuestionTextById(id);
        switch (id) {
            case 0: expectedText = "Ты потерял память. Принять вызов НЛО?"; break;
            case 1: expectedText = "Ты принял вызов. Поднимаешься на мостик к капитану?"; break;
            case 2: expectedText = "Ты поднялся на мостик. Ты кто?"; break;
        };
        assertEquals(expectedText, text);
    }

    @Test
    void checkNegativeIsLastQuestionById() {
        assertFalse(questRepository.isLastQuestionById(1));
    }

    @Test
    void checkPositiveIsLastQuestionById() {
        assertTrue(questRepository.isLastQuestionById(2));
    }

    @Test
    void checkGetAnswersByQuestionId() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Подняться на мостик", 2));
        answers.add(new Answer(2, "Отказаться подниматься на мостик", -2));
        assertEquals(answers.get(0).getText(), questRepository.getAnswersByQuestionId(1).get(0).getText());
        assertEquals(answers.get(1).getText(), questRepository.getAnswersByQuestionId(1).get(1).getText());
    }
}
