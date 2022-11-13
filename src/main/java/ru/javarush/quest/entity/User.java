package ru.javarush.quest.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String ipAddress;
    private int countGames;
    private int countWin;

    public User(String name) {
        this.name = name;
        this.countGames = 1;
        this.countWin = 0;
    }

    public void incrCountGames() {
        countGames++;
    }

    public void incrWin() {
        countWin++;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", countGames=" + countGames +
                ", countWin=" + countWin +
                '}';
    }
}
