package ru.javarush.quest;

import lombok.extern.slf4j.Slf4j;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.repository.QuestRepository;
import ru.javarush.quest.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quest")
@Slf4j
public class QuestServlet extends HttpServlet {
    private QuestRepository questRepository;
    private UserRepository userRepository;

    @Override
    public void init() {
        try {
            questRepository = new QuestRepository();
            if (userRepository == null) {
                userRepository = new UserRepository();
                log.info("Creating userRepository");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        User user;

        if (!userRepository.isExists(userName)) {
            user = new User(userName);
            userRepository.save(userName, user);
            log.info("Creating new User " + userName);
        } else {
            user = userRepository.getUserByName(userName);
            log.info("User " + userName + " is exists");
        }

        int nextQuestionId = Integer.parseInt(request.getParameter("nextQuestionId"));
        boolean isLastQuestion = Boolean.parseBoolean(request.getParameter("isLastQuestion"));
        boolean isWrongAnswer = checkNegativeNumber(nextQuestionId);
        String question = questRepository.getQuestionTextById(nextQuestionId);

        log.info(user.toString() + ", nextQuestionId = " + nextQuestionId + ", isLastQuestion = " + isLastQuestion +
                ", isWrongAnswer = " + isWrongAnswer + ", question = " + question);

        if (!isLastQuestion && !isWrongAnswer){
            List<Answer> answersByQuestion = questRepository.getAnswersByQuestionId(nextQuestionId);
            isLastQuestion = questRepository.isLastQuestionById(nextQuestionId);
            request.setAttribute("question", question);
            request.setAttribute("answers", answersByQuestion);
            request.setAttribute("nextQuestonId", nextQuestionId);
            request.setAttribute("isLastQuestion", isLastQuestion);
            request.setAttribute("userName", user.getName());
            request.setAttribute("countGames", user.getCountGames());
            request.setAttribute("countWin", user.getCountWin());
            request.getRequestDispatcher("quest.jsp").forward(request, response);
        } else if (isLastQuestion && !isWrongAnswer) {
            user.incrCountGames();
            user.incrWin();
            request.setAttribute("text",question);
            log.info(user.getName() + " win! " + " CountGames = " + user.getCountGames() + ". CountWin = " + user.getCountWin());
            request.getRequestDispatcher("final.jsp").forward(request, response);
        } else {
            user.incrCountGames();
            request.setAttribute("text",question);
            log.info(user.getName() + " lost! " + " CountGames = " + user.getCountGames() + ". CountWin = " + user.getCountWin());
            request.getRequestDispatcher("final.jsp").forward(request, response);
        }
    }

    private boolean checkNegativeNumber(int nextQuestonId) {
        return nextQuestonId < 0;
    }
}
