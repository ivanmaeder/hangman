package net.redchamp.hangman.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.mongodb.*;

import net.redchamp.hangman.data.Phrase;
import net.redchamp.hangman.domain.Hangman;

/**
 * Begin a new hangman game.
 *
 * Initialise a new game of hangman in this session, then returns the
 * details of the new game.
 */
public class NewGameServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hangman game = new Hangman(Phrase.fetchRandomPhrase());

        request.getSession(true).setAttribute("currentGame", game);

        response.getWriter().println(game.toJSON());
    }
}
