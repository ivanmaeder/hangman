/**
 * A hangman game.
 *
 * Services are STATEFUL; game state is kept on the server. Two services
 * exist to start a new game or advance in the current game.
 *
 * 1.
 * BEGIN A NEW GAME
 * To begin a game or (or abandon and start a new game),
 *
 *     Request: GET /game/
 *     Response: {
 *         phrase: "Word or phrase",
 *         remaining_moves: 6
 *     }
 *
 * 2.
 * ADVANCE IN THE CURRENT GAME
 * To make a move (take a guess).
 *
 * Correct guesses do not alter `remaining_moves` (these guesses are free).
 *
 * Value `win` will be true when all the letters are guessed; a lost game
 * is defined by `win` being false and `remaining_moves` being zero.
 *
 *     Request: POST /move/?letter=Q
 *     Response: {
 *         win: false,
 *         remaining_moves: 4
 *     }
 */
package net.redchamp.hangman;
