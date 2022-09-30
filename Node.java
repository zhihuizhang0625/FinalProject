/**
 * Represents a single node in the trie
 * 
 * @author Mati Davis
 *
 */
public class Node {
    /*
     * the weight of the letter at the current node
     */
    long wt;
    
    /*
     * the letter at the current node
     */
    char c;
    
    /*
     * The parent of the current node in the trie
     */
    Node parent;
    /*
     * The 26 children of the current node (letters of the alphabet)
     * all of which can contain a node or be null
     */
    Node[] children;
    
    public Node(char c, long wt, Node parent) {
        this.wt = wt;
        this.c = c;
        this.parent = parent;
        children = new Node[26];
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
    
    /**
     * 
     * @return the weight of the current node
     */
    public long getWeight() {
        return wt;
    }
    
    /**
     * 
     * @param weight - the weight we would like to 
     * set the current node to
     */
    public void setWeight(long weight) {
        wt = weight;
    }
    
    /**
     * 
     * @return the letter the node represents
     */
    public char getChar() {
        return c;
    }
    
    /**
     * 
     * @return the 26 children of the node, some or all of
     * which may be null
     */
    public Node[] getChildren() {
        return children;
    }
    
    /*
     * 
     * @param children = the children we would like to
     * set current children to
     */
    public void setChildren(Node[] children) {
        this.children = children;
    }
    
    /**
     * 
     * @return the parent of the current node
     */
    public Node getParent() {
        return parent;
    }
}
