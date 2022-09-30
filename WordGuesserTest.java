import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

class WordGuesserTest {

    @Test
    void testGuessWord() {
        Trie t = new Trie();
        t.buildTrie("test_files/test.txt");
        WordGuesser w = new WordGuesser(t);
        Collection<String> c = new HashSet<String>();
        assertEquals("bbbbb", w.guessWord(c));
        w.w.setLetter('a', 1);
        assertEquals("abbbb", w.guessWord(c));
        w.w.notInRightSpot('b', 4);
        assertEquals("abbab", w.guessWord(c));
        w.w.notInRightSpot('b', 2);
        assertEquals("aabab", w.guessWord(c));
        w.w.notInWord('b');
        assertEquals("", w.guessWord(c));
        WordGuesser w2 = new WordGuesser(t);
        w2.w.notInWord('b');
        assertEquals("aaaaa", w2.guessWord(c));
    }

    @Test
    void testGuessWords() {
        Trie t = new Trie();
        t.buildTrie("test_files/test.txt");
        WordGuesser w = new WordGuesser(t);
        List<String> guessed = w.guessWords(5);
        assertEquals("bbbbb", guessed.get(0));
        assertEquals("bbbab", guessed.get(1));
        assertEquals("bbabb", guessed.get(2));
        assertEquals("bbaab", guessed.get(3));
        assertEquals("babbb", guessed.get(4));
    }

    @Test
    void testUpdateWord() {
        Trie t = new Trie();
        t.buildTrie("test_files/test.txt");
        WordGuesser w = new WordGuesser(t);

        Collection<Character> notInWord = new HashSet<Character>();
        notInWord.add('a');
        notInWord.add('b');
        notInWord.add('c');

        HashMap<Integer, Character> notInRightSpot = new HashMap<Integer, Character>();
        notInRightSpot.put(0, 'y');
        notInRightSpot.put(1, 'z');

        HashMap<Integer, Character> guessedCorrectly = new HashMap<Integer, Character>();
        guessedCorrectly.put(4, 'j');
        guessedCorrectly.put(3, 'k');
        guessedCorrectly.put(2, 'l');

        w.updateWord(notInWord, notInRightSpot, guessedCorrectly);

        notInRightSpot.put(0, 'x');
        w.updateWord(notInWord, notInRightSpot, guessedCorrectly);

        assertFalse(w.w.inWord('a'));
        assertFalse(w.w.inWord('b'));
        assertFalse(w.w.inWord('c'));

        assertTrue(w.w.inWordNotRightSpot('x'));
        assertTrue(w.w.inWordNotRightSpot('y'));
        assertTrue(w.w.inWordNotRightSpot('z'));

        Letter x = w.w.getLetter(1);
        assertFalse(x.canBe('x'));
        assertFalse(x.canBe('y'));
        assertTrue(x.canBe('z'));
        assertEquals('\0', x.getChar());

        x = w.w.getLetter(2);
        assertTrue(x.canBe('x'));
        assertTrue(x.canBe('y'));
        assertFalse(x.canBe('z'));
        assertEquals('\0', x.getChar());

        x = w.w.getLetter(3);
        assertEquals('l', x.getChar());
        assertTrue(x.canBe('x'));
        assertTrue(x.canBe('y'));
        assertTrue(x.canBe('z'));
    }

    @Test
    void testGetTenMostCommon() {
        Trie t = new Trie();
        t.buildTrie("test_files/test.txt");
        WordGuesser w = new WordGuesser(t);
        List<String> ten = w.getTenMostCommon();
        assertEquals("bbbbb", ten.get(0));
        assertEquals("bbbba", ten.get(1));
        assertEquals("bbbab", ten.get(2));
        assertEquals("bbbaa", ten.get(3));
    }

}
