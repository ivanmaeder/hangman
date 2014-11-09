package net.redchamp.hangman.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.mongodb.*;

import net.redchamp.hangman.data.*;
import net.redchamp.hangman.domain.*;

public class NewGameServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hangman game = new Hangman(Phrase.fetchRandomPhrase());

        request.getSession(true).setAttribute("currentGame", game);

        response.getWriter().println(game.toJSON());
    }
}
