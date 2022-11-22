package ru.javarush.quest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {

    QuestService questService = new QuestService();
    private static final String QUEST_NAME = "space";

    public QuestServiceTest() throws IOException {
        questService.addQuest(QUEST_NAME, "quest.json");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void checkGetQuestionTextById(int id) {
        String expectedText = "";
        String text = questService.getQuestionTextById(QUEST_NAME, id);
        switch (id) {
            case 0: expectedText = "Ты потерял память. Принять вызов НЛО?"; break;
            case 1: expectedText = "Ты принял вызов. Поднимаешься на мостик к капитану?"; break;
            case 2: expectedText = "Ты поднялся на мостик. Ты кто?"; break;
        };
        assertEquals(expectedText, text);
    }

    @Test
    void checkNegativeIsLastQuestionById() {
        assertFalse(questService.isLastQuestionById(QUEST_NAME, 1));
    }

    @Test
    void checkPositiveIsLastQuestionById() {
        assertTrue(questService.isLastQuestionById(QUEST_NAME, 2));
    }

    @Test
    void checkGetAnswersByQuestionId() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1, "Подняться на мостик", 2));
        answers.add(new Answer(2, "Отказаться подниматься на мостик", -2));
        assertEquals(answers.get(0).getText(), questService.getAnswersByQuestionId(QUEST_NAME,1).get(0).getText());
        assertEquals(answers.get(1).getText(), questService.getAnswersByQuestionId(QUEST_NAME,1).get(1).getText());
    }

    @Test
    void checkSaveAndGetUserByName() {
        String name = "Petya";
        User user = new User(name);
        questService.saveUser(name, user);
        assertEquals(user.getName(), questService.getUserByName(name).getName());
    }

    @Test
    void checkNegativeIsExists() {
        assertFalse(questService.isUserExists("Vanya"));
    }

    @Test
    void checkPositiveIsExists() {
        User user = new User("Masha");
        questService.saveUser("Masha", user);
        assertTrue(questService.isUserExists("Masha"));
    }
}
