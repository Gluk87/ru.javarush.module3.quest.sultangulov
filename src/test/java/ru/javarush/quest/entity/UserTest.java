package ru.javarush.quest.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    User user = new User("Ivan");

    @Test
    void checkIncrCountGames() {
        user.incrCountGames();
        assertEquals(2, user.getCountGames());
    }

    @Test
    void checkIncrWin() {
        user.incrWin();
        assertEquals(1, user.getCountWin());
    }

    @Test
    void checkToString() {
        User newUser = new User("Masha");
        assertEquals("User{name='Masha', countGames=1, countWin=0}", newUser.toString());
    }
}
