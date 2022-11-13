package ru.javarush.quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;

@WebServlet(name = "startServlet", value = "/start")
public class StartServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        String userName = request.getParameter("userName");
        request.setAttribute("userName", userName);
        session.setAttribute("IP", InetAddress.getLocalHost().getHostAddress());

        getServletContext()
                .getRequestDispatcher("/quest")
                .forward(request, response);

    }
}