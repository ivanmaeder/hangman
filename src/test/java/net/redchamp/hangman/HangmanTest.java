package net.redchamp.hangman;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HangmanTest extends TestCase {
    public HangmanTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(HangmanTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}
