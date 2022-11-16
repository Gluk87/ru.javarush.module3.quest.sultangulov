package ru.javarush.quest.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String text;
    private Integer nextQuestionId;
}
