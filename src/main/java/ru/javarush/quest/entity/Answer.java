package ru.javarush.quest.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Answer {
    private int id;
    private String text;
    private Integer nextQuestionId;
    private boolean isWrongAnswer;
}
