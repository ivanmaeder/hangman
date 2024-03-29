package net.redchamp.hangman.domain;

import java.util.*;

import org.json.*;

/**
 * A game of hangman.
 *
 * The logic of starting and advancing a game of hangman is contained
 * herel. An object of this class maintains the state of a game and can
 * be queried to determine progress.
 *
 * Begin by instantiating an object, and advance the game through
 * `chooseLetter(char)`.
 */
public class Hangman {
    public static final int POSSIBLE_MOVES = 6;

    private String phrase;
    private List<Character> correctlyChosenLetters;
    private List<Character> incorrectlyChosenLetters;

    public Hangman(String phrase) {
        this.phrase = phrase.toUpperCase();

        this.correctlyChosenLetters = new ArrayList<Character>();
        this.incorrectlyChosenLetters = new ArrayList<Character>();
    }

    public static Hangman newFromJSON(String json) {
        return new Hangman(new JSONObject(json).getString("phrase"));
    }

    public String getPhrase() {
        return this.phrase;
    }

    public List<Character> getCorrectlyChosenLetters() {
        return this.correctlyChosenLetters;
    }

    public List<Character> getIncorrectlyChosenLetters() {
        return this.incorrectlyChosenLetters;
    }

    /**
     * Make a move.
     *
     * @return true if the letter is contained in the phrase; false
     * otherwise
     */
    public boolean chooseLetter(char letter) {
        if (this.phrase.indexOf(letter) >= 0) {
            this.correctlyChosenLetters.add(letter);
            return true;
        }
        else {
            this.incorrectlyChosenLetters.add(letter);
            return false;
        }
    }

    public int calculateRemainingMoves() {
        return Hangman.POSSIBLE_MOVES - incorrectlyChosenLetters.size();
    }

    public boolean calculateWinStatus() {
        Set<Character> remainingLetters = new HashSet<Character>();

        int stringLength = this.phrase.length();
        for (int i = 0; i < stringLength; i++) {
            char c = this.phrase.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                remainingLetters.add(this.phrase.charAt(i));
            }
        }

        for (char correctlyChosenLetter : getCorrectlyChosenLetters()) {
            remainingLetters.remove(correctlyChosenLetter);
        }

        return remainingLetters.size() == 0;
    }

    public String toJSON() {
        return "{\n" +
               "    \"phrase\": \"" + this.phrase + "\",\n" +
               "    \"win\": " + calculateWinStatus() + ",\n" +
               "    \"remaining_moves\": " + calculateRemainingMoves() + "\n" +
               "}";
    }
}
