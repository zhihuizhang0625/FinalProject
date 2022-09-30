import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TrieTest {

    @Test
    public void testGetRoot() {
        Trie trie = new Trie();
        assertEquals(' ', trie.getRoot().getChar());
        assertEquals(0, trie.getRoot().getWeight());   
    }
    
    @Test
    public void testAddWord() {
        Trie trie = new Trie();
        Node root = trie.getRoot();
        trie.addWord("aaa", 3);
        trie.addWord("bbb", 2);
        assertEquals('a',root.getChildren()[0].getChar());
        assertEquals('a',root.getChildren()[0].getChildren()[0].getChar());
        assertEquals('a',root.getChildren()[0].getChildren()[0].getChildren()[0].getChar());
        assertEquals(3, root.getChildren()[0].getWeight());
        assertEquals(3, root.getChildren()[0].getChildren()[0].getWeight());
        assertEquals(3, root.getChildren()[0].getChildren()[0].getChildren()[0].getWeight());
        assertEquals('b', root.getChildren()[1].getChar());
        assertEquals(2, root.getChildren()[1].getWeight());
    }
    @Test
    public void testBuildTrie() {
        Trie trie = new Trie();
        trie.buildTrie("test.txt");
        Node root = trie.getRoot();
        assertEquals('a',root.getChildren()[0].getChar());
        assertEquals('a',root.getChildren()[0].getChildren()[0].getChar());
        assertEquals('a',root.getChildren()[0].getChildren()[0].getChildren()[0].getChar());
        assertEquals(10, trie.getMostCommon().size());
        assertEquals("abdda", trie.getMostCommon().get(0));
        assertEquals("aabbc", trie.getMostCommon().get(1));
        assertEquals(24, root.getChildren()[0].getChildren()[1].getWeight());
        assertEquals(41, root.getChildren()[0].getWeight());
        assertEquals(16, root.getChildren()[0].getChildren()[0].getChildren()[1].getWeight());
        assertEquals(11, root.getChildren()[2].getChildren()[2].getChildren()[2].getWeight());
        
    }
    
    @Test
    public void testSetRoot() {
        Trie trie = new Trie();
        trie.buildTrie("test.txt");
        Node root = new Node('a', 10, null);
        trie.setRoot(root);
        assertEquals('a', trie.getRoot().c);
        assertEquals(10, trie.getRoot().wt);
        
    }

}
