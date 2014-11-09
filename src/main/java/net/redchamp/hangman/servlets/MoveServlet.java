package net.redchamp.hangman.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.mongodb.*;

import net.redchamp.hangman.domain.*;

public class MoveServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hangman game = (Hangman) request.getSession().getAttribute("currentGame");

        game.chooseLetter(request.getParameter("letter").charAt(0));

        response.getWriter().println(game.toJSON());
    }
}
