package ru.javarush.quest.servlet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestServletTest {
    QuestServlet questServlet = new QuestServlet();

    @Test
    void checkUserRepositoryInit() {
        questServlet.init();
        assertNotNull(questServlet.getQuestRepository());
    }

    @Test
    void checkQuestRepositoryInit() {
        questServlet.init();
        assertNotNull(questServlet.getQuestRepository());
    }
}
