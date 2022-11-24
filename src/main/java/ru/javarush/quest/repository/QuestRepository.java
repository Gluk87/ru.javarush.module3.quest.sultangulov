package ru.javarush.quest.repository;

import lombok.Getter;
import ru.javarush.quest.entity.Quest;

import java.util.HashMap;

@Getter
public class QuestRepository {
    private final HashMap<String, Quest> quests = new HashMap<>();
}
