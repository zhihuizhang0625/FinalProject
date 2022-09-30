import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WordGuesser implements IWordGuesser {
    Trie t;
    Word w;

    public WordGuesser(Trie t) {
        this.t = t;
        w = new Word();
    }

    @Override
    public String guessWord(Collection<String> alreadyHave) {
        int level = 0;
        String ans = "";
        Node cur = t.getRoot();
        Set<Character> lettersAdded = new HashSet<Character>();
        Set<Node> blocked = new HashSet<Node>();
        while (level < 5) { // check that a five letter word has been created
            Node[] children = cur.getChildren();
            // all the characters the next letter cannot be
            Set<Character> notAllowed = new HashSet<Character>(); 
            // all the characters that must be in the word, but don't have their spot yet
            Set<Character> couldBe = new HashSet<Character>(); 
            notAllowed.addAll(w.getNotInLetter(level + 1));
            notAllowed.addAll(w.getNotInWord());
            couldBe.addAll(w.getNotInCorrectSpot());
            // get the most apprpriate next char
            int i = getMaxWeightNode(children, notAllowed, 
                    couldBe, w.getLetter(level + 1).getChar(), blocked);
            if (i == -1) { // if couldn't find a "legal" next node
                if (level == 0) { // if we're at the root, something has gone wrong
                    return "";
                }
                // if we can't find a legal next node, the current Node cannot be right
                blocked.add(cur);
                lettersAdded.remove(cur.getChar());
                cur = cur.getParent(); // go back up trie
                ans = ans.substring(0, ans.length() - 1); // delete last letter from ans
                level--; // go back a level

            } else if (level == 4) { // if we found a legal node and we're at the last letter
                lettersAdded.add(children[i].getChar());
                // If we haven't put all the letters in the word but not correct spot in answer
                // or if we have already guessed that word this round
                if (!lettersAdded.containsAll(couldBe) 
                        || alreadyHave.contains(ans + children[i].getChar())) {
                    blocked.add(cur); // don't go down this node again
                    lettersAdded.remove(cur.getChar());
                    cur = cur.getParent(); // go back up trie
                    ans = ans.substring(0, ans.length() - 1); // delete last letter
                    level--;
                } else { // otherwise, we're all good
                    // add the best legal next node to the answer string
                    ans += children[i].getChar(); 
                    cur = children[i]; // go down the trie to that node
                    level++; // increase the level
                }
            } else {
                lettersAdded.add(children[i].getChar()); // add the letter to the added letters
                ans += children[i].getChar(); // add the letter onto our string
                cur = children[i]; // go down trie
                level++;
            }
        }
        return ans;
    }

    private int charToIndex(char c) {
        return c - 97;
    }

    private int getMaxWeightNode(Node[] arr, 
            Set<Character> notAllowed, Set<Character> couldBe, char mustBe,
            Set<Node> blocked) {
        if (mustBe != '\0') { // if we know what the letter is
            // if that node in the trie exists
            if (arr[charToIndex(mustBe)] != null 
                    && !blocked.contains(arr[charToIndex(mustBe)])) { 
                return charToIndex(mustBe); // return the index of that node
            } else {
                return -1; // return a "no legal next node"
            }
        }
        // loop through the letters not in the correct spot and 
        // if one exists in the next node array, return its index
        for (char c : couldBe) { 
            if (!notAllowed.contains(c) && 
                    arr[charToIndex(c)] != null && !blocked.contains(arr[charToIndex(c)])) {
                return charToIndex(c);
            }
        }
        long cur = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) { // go through the children's array
            if (arr[i] != null && arr[i].getWeight() > cur && !notAllowed.contains(arr[i].getChar())
                 // update if it's a greater weight and it could be in the word   
                    && !blocked.contains(arr[i])) { 
                index = i;
                cur = arr[i].getWeight();
            }
        }
        if (cur == 0.0) { // if we haven't found any legal node, return "no legal node"
            return -1;
        }
        return index; // else, return the index of the next legal node
    }

    @Override
    public List<String> guessWords(int num) {
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < num; i++) { // loop through until we get to num
            String s = this.guessWord(words); // guess a single word
            if (s.equals("")) { // if it is an empty string, there is no more appropriate words
                break;
            }
            words.add(s); // make sure you don't choose this word again
        }
        return words;
    }

    /**
     * 
     * @return the ten most common words in the trie
     */
    public List<String> getTenMostCommon() {
        return t.getMostCommon();
    }

    @Override
    public void updateWord(Collection<Character> notInWord, 
            Map<Integer, Character> inWordButNotSpot,
            Map<Integer, Character> guessedCorrectly) {
        w.notInWord(notInWord); // update the word with the letters that are not in the word
        Set<Entry<Integer, Character>> s = inWordButNotSpot.entrySet();
        for (Entry<Integer, Character> e : s) {
            // update the letter so that we know that letter cannot be a certain character
            w.notInRightSpot(e.getValue(), e.getKey() + 1);
        }
        s = guessedCorrectly.entrySet();
        for (Entry<Integer, Character> e : s) {
            // update a certain letter if we know exactly which character it is
            w.setLetter(e.getValue(), e.getKey() + 1);
        }
    }

}
