import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents the Trie data structure
 * 
 * @author Mati Davis
 *
 */
public class Trie {
    /*
     * root of the trie
     */
    private Node root;
    /*
     * The ten most common words, set while building the trie
     */
    private List<String> tenMostCommon;

    public Trie() {
        root = new Node(' ', 0, null);
        tenMostCommon = new ArrayList<>();
    }

    /**
     * 
     * @return the root of the trie
     */
    public Node getRoot() {
        return root;
    }

    /**
     * 
     * @param - the node to set the root to
     */
    public void setRoot(Node r) {
        root = r;
    }

    /**
     * 
     * @return the ten most common words of the trie
     */
    public List<String> getMostCommon() {
        return tenMostCommon;
    }

    /**
     * This method builds the trie from a dictionary file and finds the ten most
     * common words while doing so
     * 
     * @param filepath - the dictionary from which to build the trie
     */
    public Node buildTrie(String filepath) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filepath));
            String line = in.readLine();
            Map<String, Long> map = new HashMap<>();
            while ((line = in.readLine()) != null) {
                String[] str = line.trim().split("\\t");
                if (str.length == 2) {
                    long weight = Long.parseLong(str[0].trim());

                    String word = str[1].trim();
                    if (word.length() == 5) {
                        map.put(word, weight);
                        addWord(word, weight);
                    }
                }
            }
            // put the map's entrySet into a list
            List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
            // sort the list based on values
            Collections.sort(list, byReverseWeightOrder());
            // retrieve a new list containng the top 10 elements
            List<Map.Entry<String, Long>> topTenList = list.subList(0, 10);
            for (Map.Entry<String, Long> entry : topTenList) {
                String key = entry.getKey();
                this.tenMostCommon.add(key);
            }

            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return root;
    }

    /**
     * Compares the two MapEntries in descending order by value.
     *
     * @return comparator Object
     */
    public static Comparator<Map.Entry<String, Long>> byReverseWeightOrder() {
        return new Comparator<Map.Entry<String, Long>>() {

            @Override
            public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
                // TODO Auto-generated method stub
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else if (o1.getValue() > o2.getValue()) {
                    return -1;
                }
                return 0;
            }

        };

    }

    /*
     * Add a single word and its weight into trie
     * 
     * @param word - the word we want to add weight - the weight of the word
     * 
     */

    public void addWord(String word, long weight) {
        // TODO Auto-generated method stub
        if (word == null || weight < 0) {
            return;
        }
        for (char c : word.toCharArray()) {
            if (!Character.isLetter((char) c)) {
                return;
            }
        }

        word = word.toLowerCase();
        Node[] children = root.getChildren();
        addHelper(children, root, word, weight);
    }

    /*
     * This is a helper function to addWord. It recursively adds each letter of word
     * into the trie
     * 
     * @param children - the references nodes of the parent node parent - the parent
     * node word - the word weight - weight of word
     */
    private void addHelper(Node[] children, Node parent, String word, long weight) {
        // base case
        if (word.length() == 1) {
            char c = word.charAt(0);
            int index = c - 97;
            parent.setChildren(children);
            if (children[index] == null) {
                Node lastNode = new Node(c, weight, parent);
                children[index] = lastNode;
                parent.setChildren(children);
            } else {
                System.out.println("Repeated word!");
            }
            return;
        }

        char c = word.charAt(0);
        int index = c - 97;
        if (children[index] == null) {
            // if it has no child node, create a new node
            children[index] = new Node(c, weight, parent);
            Node currNode = children[index];
            parent.setChildren(children);
            String subWord = word.substring(1);
            addHelper(currNode.getChildren(), currNode, subWord, weight);
        } else {
            // if a child node already exists, we update its weight
            Node currNode = children[index];
            currNode.setWeight(currNode.getWeight() + weight);
            String subWord = word.substring(1);
            addHelper(currNode.getChildren(), currNode, subWord, weight);
        }
    }

}
