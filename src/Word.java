import java.util.Collection;
import java.util.HashSet;

/**
 * A Word object represents the word that we are trying to guess
 * 
 * @author Mati Davis
 *
 */
public class Word {
    /*
     * an array of 5 letters that represents the individual letters (known and
     * unknown) that make up the word we are guessing
     */
    private Letter[] guessed;
    /*
     * a collection of characters that we have guessed and which Wordle has told us
     * are not in the word
     */
    private Collection<Character> notInWord;
    /*
     * a collection of characters that we have guessed and which Wordle has told us
     * are in the word but not in the correct spot
     */
    private Collection<Character> notInCorrectSpot;

    public Word() {
        guessed = new Letter[5];
        for (int i = 0; i < 5; i++) {
            guessed[i] = new Letter(i + 1);
        }
        notInWord = new HashSet<Character>();
        notInCorrectSpot = new HashSet<Character>();
    }

    /**
     * This method sets one of the letters to a certain character
     * 
     * @param c        - the character which we would like to set
     * @param position - the position at which the character resides
     */
    public void setLetter(char c, int position) {
        if (position < 1 || position > 5) {
            throw new IllegalArgumentException("position " + position + " is not a legal position");
        }
        guessed[position - 1].setChar(c);
        if (notInCorrectSpot.contains(c)) {
            notInCorrectSpot.remove(c);
        }
    }

    /**
     * This method retrieves a letter at one of the indexes in the word
     * 
     * @param position - the position at which the desired letter resides
     * @return the specified letter
     */
    public Letter getLetter(int position) {
        if (position < 1 || position > 5) {
            throw new IllegalArgumentException("position " + position + " is not a legal position");
        }
        return guessed[position - 1];
    }

    /**
     * This method memorizes which characters are in the word but were not guessed
     * in the right spot
     * 
     * @param c        - a character that is in the word but not guessed in the
     *                 right position
     * @param position - the position at which the character was guessed
     */
    public void notInRightSpot(char c, int position) {
        if (position < 1 || position > 5) {
            throw new IllegalArgumentException("position " + position + " is not a legal position");
        }
        notInCorrectSpot.add(c);
        guessed[position - 1].cannotBe(c);
    }

    public Collection<Character> getNotInCorrectSpot() {
        return this.notInCorrectSpot;
    }

    /**
     * This method memorizes which characters were guessed but were not in the word
     * 
     * @param c - a character that was guessed but is not in the word
     */
    public void notInWord(char c) {
        notInWord.add(c);
    }

    public void notInWord(Collection<Character> c) {
        notInWord.addAll(c);
    }

    /**
     * 
     * @return return all the characters that cannoe be in this word
     */
    public Collection<Character> getNotInWord() {
        return notInWord;
    }

    /**
     * 
     * @param position - the position in guessed of the letter you are looking for
     * @return
     */
    public Collection<Character> getNotInLetter(int position) {
        if (position < 1 || position > 5) {
            throw new IllegalArgumentException("position " + position + " is not a legal position");
        }
        return guessed[position - 1].getCannotBe();
    }

    /**
     * This method tells us whether a given character is in the word but has not
     * been guessed in the right spot yet
     * 
     * @param c - a character
     * @return whether c has been guessed but is not in the right spot yet
     */
    public boolean inWordNotRightSpot(char c) {
        return notInCorrectSpot.contains(c);
    }

    /**
     * This method tells us whether a given letter is in the word at all based on
     * the letters we have guessed so far
     * 
     * @param c - character
     * @return whether c is in the word
     */
    public boolean inWord(char c) {
        return !(notInWord.contains(c));
    }

    /**
     * puts the current state of the word into string form
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < 5; i++) {
            if (guessed[i].getChar() != '\0') {
                s += guessed[i].getChar();
            } else {
                s += '_';
            }
        }
        return s;
    }

}
