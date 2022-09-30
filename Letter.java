import java.util.Collection;
import java.util.HashSet;
/**
 * A Letter object represents one of the 5 characters 
 * that are included in a Wordle "Word" object.
 * @author Mati Davis
 *
 */
public class Letter {
    /*
     *the position (0, 1, 2, 3, or 4) in the Word that the character is in
     */
    private int position; 
    /*
     * a collection of characters in the word that through guessing, we have 
     * deduced the letter cannot be
     */
    private Collection<Character> cannotBe;
    
    /*
     * nce we have found the correct letter, we set isDefinitely to that character
     */
    private Character isDefinitely;
    
    public Letter(int position) {
        this.position = position;
        cannotBe = new HashSet<Character>();
        isDefinitely = '\0';
    }
    
    /**
     * Set the letter to a definite character
     * @param c - the character that the letter is definitely
     */
    public void setChar(char c) {
        isDefinitely = c;
    }
    /**
     * 
     * @return the letter's position
     */
    public int getPos() {
        return position;
    }
    /**
     * 
     * @return the letter's current character, 
     * '\0' if we have not found the correct one yet
     */
    public char getChar() {
        return isDefinitely;
    }
    /**
     * 
     * @param c - a letter that is in the word but cannot
     * be at this letter's position
     */
    public void cannotBe(char c) {
        cannotBe.add(c);
    }
    
    /**
     * 
     * @return a collection of characters that this letter cannot be
     */
    public Collection<Character> getCannotBe() {
        return cannotBe;
        
    }
    /**
     * 
     * @param c - a character which we are trying to see 
     * whether it could be at this position in the Word
     * @return whether the provided character can be at 
     * this position in the word
     */
    public boolean canBe(char c) {
        return !(cannotBe.contains(c));
    }

}