package ru.javarush.quest.servlet;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.service.QuestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quest")
@Slf4j
@Getter
public class QuestServlet extends HttpServlet {
    private QuestService questService;
    private static final String QUEST_NAME = "space";

    @Override
    public void init() {
        try {
            questService = new QuestService();
            questService.addQuest(QUEST_NAME, "quest.json");
            log.info("Creating questRepository and userRepository");
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String userName = request.getParameter("userName");
        User user;

        if (!questService.isUserExists(userName)) {
            user = new User(userName);
            questService.saveUser(userName, user);
            log.info("Creating new User " + userName);
        } else {
            user = questService.getUserByName(userName);
            log.info("User " + userName + " is exists");
        }

        int nextQuestionId = Integer.parseInt(request.getParameter("nextQuestionId"));
        boolean isLastQuestion = Boolean.parseBoolean(request.getParameter("isLastQuestion"));
        boolean isWrongAnswer = checkNegativeNumber(nextQuestionId);
        String question = questService.getQuestionTextById(QUEST_NAME, nextQuestionId);

        log.info(user.toString() + ", nextQuestionId = " + nextQuestionId + ", isLastQuestion = " + isLastQuestion +
                ", isWrongAnswer = " + isWrongAnswer + ", question = " + question);

        try {
            if (!isLastQuestion && !isWrongAnswer){
                List<Answer> answersByQuestion = questService.getAnswersByQuestionId(QUEST_NAME, nextQuestionId);
                isLastQuestion = questService.isLastQuestionById(QUEST_NAME, nextQuestionId);
                request.setAttribute("question", question);
                request.setAttribute("answers", answersByQuestion);
                request.setAttribute("nextQuestionId", nextQuestionId);
                request.setAttribute("isLastQuestion", isLastQuestion);
                request.setAttribute("userName", user.getName());
                request.setAttribute("countGames", user.getCountGames());
                request.setAttribute("countWin", user.getCountWin());
                request.getRequestDispatcher("quest.jsp").forward(request, response);
            } else if (isLastQuestion && !isWrongAnswer) {
                user.incrCountGames();
                user.incrWin();
                request.setAttribute("text", question);
                log.info(user.getName() + " win! " + " CountGames = " + user.getCountGames() + ". CountWin = " + user.getCountWin());
                request.getRequestDispatcher("final.jsp").forward(request, response);
            } else {
                user.incrCountGames();
                request.setAttribute("text", question);
                log.info(user.getName() + " lost! " + " CountGames = " + user.getCountGames() + ". CountWin = " + user.getCountWin());
                request.getRequestDispatcher("final.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean checkNegativeNumber(int nextQuestionId) {
        return nextQuestionId < 0;
    }
}
