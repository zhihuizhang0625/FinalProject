import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * @author Zhihui Zhang
 */
public class Generator implements IGenerator {
    private WordGuesser w;
    private ArrayList<String> wordlist = new ArrayList<>();

    public Generator(WordGuesser w) {
        this.w = w;
    }

    /**
     * load words from enDictionary.txt to an arraylist
     * 
     * @return a list of words that consists of 5 characters
     */
    public ArrayList<String> loadDict(String filename) {

        BufferedReader br = null;
        try {
            // read content from the dictionary
            br = new BufferedReader(new FileReader(filename));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.trim().split("\\t");
                if (str.length == 2) {
                    String word = str[1];
                    HashSet<Boolean> isLetter = new HashSet<>();
                    for (int i = 0; i < word.length(); i++) {
                        if (!Character.isLetter(word.charAt(i))) {
                            isLetter.add(false);
                        }
                    }
                    // if the word has correct format
                    if (!isLetter.contains(false) && word.length() == 5) {
                        wordlist.add(word);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordlist;
    }

    /**
     * randomly generate a word as the answer
     * 
     * @param word list
     * @return the randomly selected word
     */
    public String generateWord(ArrayList<String> wordlist) {
        int length = wordlist.size();
        // generater random integer within limit
        Random rand = new Random();
        int randomnum = rand.nextInt(length);
        String randomword = wordlist.get(randomnum);
        return randomword;
    }

    public WordGuesser getW() {
        return w;
    }

    public void setW(WordGuesser w) {
        this.w = w;
    }

    public ArrayList<String> getWordlist() {
        return wordlist;
    }

    public void setWordlist(ArrayList<String> wordlist) {
        this.wordlist = wordlist;
    }

}
