package net.redchamp.hangman;

import java.util.*;

import junit.framework.*;
import org.json.*;
import org.mockito.*;

import net.redchamp.hangman.data.*;

import static junit.framework.Assert.*;

public class PhraseTest extends Mockito {
    public void testRandomPhrase() throws Exception {
        String phrase = Phrase.fetchRandomPhrase();

        assertTrue(phrase.length() > 0);
    }
}
