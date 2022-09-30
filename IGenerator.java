import java.util.ArrayList;

/**
 * @author Zhihui Zhang
 */
public interface IGenerator {

    /**
     * load words from enDictionary.txt to an arraylist
     * 
     * @param file path
     * @return a list of words that consists of 5 characters
     */
    public ArrayList<String> loadDict(String filename);

    /**
     * randomly generate a word as the answer
     * 
     * @param word list
     * @return the randomly selected word
     */
    public String generateWord(ArrayList<String> wordlist);

}
