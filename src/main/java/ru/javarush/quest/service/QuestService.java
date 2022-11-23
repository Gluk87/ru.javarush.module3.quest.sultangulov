package ru.javarush.quest.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.Quest;
import ru.javarush.quest.entity.Question;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.exception.QuestionNotFoundException;
import ru.javarush.quest.repository.QuestRepository;
import ru.javarush.quest.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
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
        Optional<Question> question = getQuestion(questName, id);
        if (question.isPresent()) {
            return question.get().getText();
        } else {
            throw new QuestionNotFoundException("Question for quest = " + questName + " and id = " + id + " not found");
        }

    }

    public boolean isLastQuestionById(String questName, int id) {
        Optional<Question> question = getQuestion(questName, id);
        if (question.isPresent()) {
            return question.get().isLastQuestion();
        } else {
            throw new QuestionNotFoundException("Question for quest = " + questName + " and id = " + id + " not found");
        }
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

    private Optional<Question> getQuestion(String questName, int id) {
        return questRepository.getQuests().get(questName).getQuestions().stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    private Quest getQuestFromFile(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        return new JsonMapper().readValue(file, Quest.class);
    }
}
