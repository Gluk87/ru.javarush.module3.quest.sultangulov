package ru.javarush.quest.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ru.javarush.quest.entity.Quest;

import java.io.File;
import java.io.IOException;

public class QuestService {

    public Quest getQuestFromFile(String fileName) throws IOException {
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        return new JsonMapper().readValue(file, Quest.class);
    }
}
