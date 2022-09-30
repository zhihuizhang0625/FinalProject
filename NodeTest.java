import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class NodeTest {

    @Test
    public void testGetWeight() {
        Node node = new Node('t', 5, null);
        assertEquals(5, node.getWeight());
    }

    @Test
    public void testGetChar() {
        Node node = new Node('t', 5, null);
        assertEquals('t', node.getChar());

    }

    @Test
    public void testSetWeight() {
        Node node = new Node('t', 5, null);
        node.setWeight(10);
        assertEquals(10, node.getWeight());
    }

    @Test
    public void testSetChildren() {
        Node node = new Node('t', 5, null);
        Node[] children = new Node[26];
        Node node1 = new Node('a', 1, node);
        children[0] = node1;
        assertEquals(node1, children[0]);
        assertNull(children[1]);
    }

    @Test
    public void testGetChildren() {
        Node node = new Node('t', 5, null);
        Node[] children = new Node[26];
        Node node1 = new Node('a', 1, node);
        children[0] = node1;
        node.setChildren(children);
        assertEquals(node1, node.getChildren()[0]);
        assertNull(node.getChildren()[1]);
    }

    @Test
    public void testGetParent() {
        Node node = new Node('t', 5, null);
        Node[] children = new Node[26];
        Node node1 = new Node('a', 1, node);
        children[0] = node1;
        assertEquals(node, node1.getParent());
    }

}
