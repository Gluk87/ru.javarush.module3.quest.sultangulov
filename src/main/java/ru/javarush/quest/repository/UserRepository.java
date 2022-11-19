package ru.javarush.quest.repository;

import ru.javarush.quest.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> repository = new HashMap<>();

    public void save(String name, User user) {
        repository.put(name, user);
    }

    public User getUserByName(String name) {
        return repository.get(name);
    }

    public boolean isExists(String name) {
        return repository.containsKey(name);
    }
}
