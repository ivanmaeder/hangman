package net.redchamp.hangman;

import java.util.*;

import junit.framework.*;
import org.json.*;
import org.mockito.*;

import net.redchamp.hangman.domain.*;

import static junit.framework.Assert.*;

public class HangmanTest extends Mockito {
    public void testParseFromJSON() throws Exception {
        Hangman game = Hangman.newFromJSON("{ phrase: 'Example Phrase' }");

        assertEquals(game.getPhrase(), "Example Phrase");
    }

    public void testCorrectlyChosenLetters() throws Exception {
        List<Character> expected = new ArrayList<Character>();
        expected.add('E');

        Hangman game = new Hangman("Example Phrase");
        game.chooseLetter('E');
        List<Character> found = game.getCorrectlyChosenLetters();

        assertEquals(expected, found);
    }

    public void testRemainingMovesAfterCorrectChoice() throws Exception {
        Hangman game = new Hangman("Example Phrase");
        game.chooseLetter('E');

        assertEquals(Hangman.POSSIBLE_MOVES, game.calculateRemainingMoves());
    }

    public void testIncorrectlyChosenLetters() throws Exception {
        List<Character> expected = new ArrayList<Character>();
        expected.add('Z');

        Hangman game = new Hangman("Example Phrase");
        game.chooseLetter('Z');
        List<Character> found = game.getIncorrectlyChosenLetters();

        assertEquals(expected, found);
    }

    public void testRemainingMovesAfterIncorrectChoice() throws Exception {
        Hangman game = new Hangman("Example Phrase");
        game.chooseLetter('Z');

        assertEquals(Hangman.POSSIBLE_MOVES - 1, game.calculateRemainingMoves());
    }

    public void testToJSON() {
        Hangman game = new Hangman("Example Phrase");

        JSONObject obj = new JSONObject(game.toJSON());

        assertTrue(obj.getString("phrase").length() > 0);
        assertTrue(obj.getBoolean("win") ? true : true);
        assertTrue(obj.getInt("remaining_moves") == 6);
    }
}
