package net.redchamp.hangman;

import java.io.*;
import javax.servlet.http.*;

import junit.framework.*;
import org.json.*;
import org.mockito.*;

import net.redchamp.hangman.domain.*;
import net.redchamp.hangman.servlets.*;

import static junit.framework.Assert.*;

public class LifecycleTest extends Mockito {
    public void testNewGame() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        mockSession(request);

        StringWriter out = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(out));

        new NewGameServlet().doGet(request, response);
        Hangman game = Hangman.newFromJSON(out.toString());

        assertTrue(game.getPhrase().length() > 0);
        assertTrue(game.calculateRemainingMoves() == 6);
    }

    public void testCorrectMove() throws Exception {
        //begin new game
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        mockSession(request);

        StringWriter out = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(out));

        new NewGameServlet().doGet(request, response);
        Hangman game = Hangman.newFromJSON(out.toString());

        //make a move
        when(request.getSession().getAttribute("currentGame")).thenReturn(game);

        when(request.getParameter("letter")).thenReturn(String.valueOf(game.getPhrase().charAt(0)));
        new MoveServlet().doPost(request, response);

        assertTrue(game.calculateRemainingMoves() == 6);
    }

    public void testIncorrectMove() throws Exception {
        //begin new game
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        mockSession(request);

        StringWriter out = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(out));

        new NewGameServlet().doGet(request, response);
        Hangman game = Hangman.newFromJSON(out.toString());

        //make a move
        when(request.getSession().getAttribute("currentGame")).thenReturn(game);

        when(request.getParameter("letter")).thenReturn("Ø");
        new MoveServlet().doPost(request, response);

        assertTrue(game.calculateRemainingMoves() == 5);
    }

    private static HttpSession mockSession(HttpServletRequest request) {
        HttpSession session = mock(HttpSession.class);    

        when(request.getSession(true)).thenReturn(session);
        when(request.getSession()).thenReturn(session);

        return session;
    }
}
