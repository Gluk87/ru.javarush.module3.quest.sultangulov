package ru.javarush.quest.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.Quest;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.repository.QuestRepository;
import ru.javarush.quest.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestService {

    private final QuestRepository questRepository;
    private final UserRepository userRepository;

    public QuestService() {
        this.questRepository = new QuestRepository();
        this.userRepository = new UserRepository();
    }

    public void addQuest(String questName, String fileName) throws IOException {
        questRepository.getQuests().put(questName, getQuestFromFile(fileName));
    }

    public String getQuestionTextById(String questName, int id) {
        return questRepository.getQuests().get(questName).getQuestions().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get()
                .getText();
    }

    public boolean isLastQuestionById(String questName, int id) {
        return questRepository.getQuests().get(questName).getQuestions().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get()
                .isLastQuestion();
    }

    public List<Answer> getAnswersByQuestionId(String questName, int id) {
        return questRepository.getQuests().get(questName).getQuestions().get(id).getAnswers();
    }

    public void saveUser(String name, User user) {
        userRepository.getRepository().put(name, user);
    }

    public User getUserByName(String name) {
        return userRepository.getRepository().get(name);
    }

    public boolean isUserExists(String name) {
        return userRepository.getRepository().containsKey(name);
    }

    private Quest getQuestFromFile(String fileName) throws IOException {
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        return new JsonMapper().readValue(file, Quest.class);
    }
}
