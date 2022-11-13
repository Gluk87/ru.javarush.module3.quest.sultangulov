package ru.javarush.quest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Quest {
    private List<Question> questions;
}
