import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Mati Davis
 *
 */
public interface IWordGuesser {
    
    
    /**
     * based on the current state of word, this method will go through 
     * a trie and find the word with the highest weight that hasn't 
     * already been guessed and meets the specification of the current
     * Word
     * 
     * @return the word with the highest weight meeting the specifications
     */
    public String guessWord(Collection<String> alreadyHave);
    
    /**
     * Given a number, this method will go through the trie and find the top "num"
     * words that have the highest weight, haven't already been guessed, and fit
     * the specifications for the current word
     * @param num - a number which tells how many words to include in the return value
     * @return a list of the words with the highest weight meeting the specifications 
     * of the current Word
     */
    public List<String> guessWords(int num);
    
    /** given the list returned from guessWords, this method will display them to the user
     * and wait for the user to select which of those words they want to input next
     * 
     * @param words - a list of the words with the highest weight meeting the specifications 
     * of the current Word
     * @return a word that the user indicates to guess next
     */
   // public String recommendWords(List<String> words);
    
    /**
     * Given the response from the Wordle website, this method updates the Word object in this class
     * with the results
     * 
     * @param notInWord-  letters guessed that are not in the word at all
     * @param inWordButNotSpot - letters guessed that are in the word but not the correct spot
     * @param guessedCorrectly - letters guessed that are in word and in the correct spot
     */
    public void updateWord(Collection<Character> notInWord, 
            Map<Integer, Character> inWordButNotSpot, 
            Map<Integer, Character> guessedCorrectly);
    
}