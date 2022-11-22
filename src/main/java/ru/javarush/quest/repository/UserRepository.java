package ru.javarush.quest.repository;

import lombok.Getter;
import ru.javarush.quest.entity.User;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UserRepository {
    private final Map<String, User> repository = new HashMap<>();
}
