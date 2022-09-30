import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

public class WordTest {
 
    @Test
    public void testWordConstructor() {
        Word w = new Word();
        assertTrue(w.getLetter(1) != null);
        assertTrue(w.getLetter(2) != null);
        assertTrue(w.getLetter(3) != null);
        assertTrue(w.getLetter(4) != null);
        assertTrue(w.getLetter(5) != null);
    }

    @Test
    public void testSetLetter() {
        Word w = new Word();
        w.setLetter('a', 1);
        assertEquals('a', w.getLetter(1).getChar());
    }

    @Test
    public void testSetLetterIllegalArgument() {
        Word w = new Word();

        assertThrows(IllegalArgumentException.class, () -> {
            w.setLetter('a', 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            w.setLetter('a', 6);
        });
    }

    @Test
    public void testNotInRightSpot() {
        Word w = new Word();
        w.notInRightSpot('c', 2);
        assertFalse(w.getLetter(2).canBe('c'));
        assertTrue(w.inWordNotRightSpot('c'));
    }

    @Test
    public void testnotInRightSpotIllegalArgument() {
        Word w = new Word();
        assertThrows(IllegalArgumentException.class, () -> {
            w.notInRightSpot('a', 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            w.notInRightSpot('a', 6);
        });
    }
    
    @Test
    public void testInWord() {
        Word w = new Word();
        w.notInWord('a');
        assertFalse(w.inWord('a'));
        w.setLetter('b', 1);
        assertTrue(w.inWord('b'));       
    }
    
    @Test
    public void testGetNotInLetter() {
        Word w = new Word();
        w.notInRightSpot('a',1);
        w.notInRightSpot('b',1);
        w.notInRightSpot('c',1);
        Collection<Character> c = w.getNotInLetter(1);
        assertEquals(3, c.size());
        
    }
    
    @Test
    public void testGetNotInCorrectSpot() {
        Word w = new Word();
        w.notInRightSpot('a',1);
        w.notInRightSpot('b',1);
        w.notInRightSpot('c',1);
        Collection<Character> c = w.getNotInCorrectSpot();
        assertEquals(3, c.size());
        assertTrue(c.contains('a'));
        assertTrue(c.contains('b'));
        assertTrue(c.contains('c'));
    }
    
    @Test
    public void testGetNotInWord() {
        Word w = new Word();
        w.notInWord('a');
        Collection<Character> c = w.getNotInWord();
        assertEquals(1, c.size());
        assertTrue(c.contains('a'));
        
    }

    @Test
    public void testToString() {
        Word w = new Word();
        assertEquals("_____", w.toString());
        w.notInRightSpot('c', 2);
        assertEquals("_____", w.toString());
        w.setLetter('a', 1);
        w.setLetter('b', 4);
        assertEquals("a__b_", w.toString());
    }

}
