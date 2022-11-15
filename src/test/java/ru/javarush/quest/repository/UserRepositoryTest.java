package ru.javarush.quest.repository;

import org.junit.jupiter.api.Test;
import ru.javarush.quest.entity.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();

    @Test
    void checkSaveAndGetUserByName() {
        String name = "Petya";
        User user = new User(name);
        userRepository.save(name, user);
        assertEquals(user.getName(), userRepository.getUserByName(name).getName());
    }

    @Test
    void checkNegativeIsExists() {
        assertFalse(userRepository.isExists("Vanya"));
    }

    @Test
    void checkPositiveIsExists() {
        User user = new User("Masha");
        userRepository.save("Masha", user);
        assertTrue(userRepository.isExists("Masha"));
    }
}
