import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GeneratorTest {

    @Test
    public void testGenerator() {
        Trie t = new Trie();
        t.buildTrie("wiktionary.txt");
        WordGuesser w = new WordGuesser(t);
        Generator g = new Generator(w);
        assertEquals(w, g.getW());
    }

    @Test
    public void testLoadDict() {
        Trie t = new Trie();
        t.buildTrie("wiktionary.txt");
        WordGuesser w = new WordGuesser(t);
        Generator g = new Generator(w);
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("aaaaa");
        wordlist.add("abbbb");
        wordlist.add("bbbbb");
        wordlist.add("abcde");
        wordlist.add("ccccc");
        wordlist.add("cccde");
        wordlist.add("aabcd");
        wordlist.add("abccd");
        wordlist.add("aabbc");
        wordlist.add("abdda");
        assertEquals(wordlist.size(), g.loadDict("test.txt").size());
        assertEquals(wordlist.get(1), g.loadDict("test.txt").get(1));
    }

    @Test
    public void testGenerateWord() {
        Trie t = new Trie();
        t.buildTrie("wiktionary.txt");
        WordGuesser w = new WordGuesser(t);
        Generator g = new Generator(w);
        assertEquals(5, g.generateWord(g.loadDict("test.txt")).length());
    }

    @Test
    public void testGetW() {
        Trie t = new Trie();
        t.buildTrie("wiktionary.txt");
        WordGuesser w = new WordGuesser(t);
        Generator g = new Generator(w);
        assertEquals(w, g.getW());
    }

    @Test
    public void testSetW() {
        Trie t1 = new Trie();
        WordGuesser w1 = new WordGuesser(t1);
        Generator g = new Generator(w1);
        assertEquals(w1, g.getW());

        Trie t2 = new Trie();
        t2.buildTrie("wiktionary.txt");
        WordGuesser w2 = new WordGuesser(t2);
        g.setW(w2);
        assertEquals(w2, g.getW());
    }

    @Test
    public void testGetWordlist() {
        Trie t = new Trie();
        t.buildTrie("wiktionary.txt");
        WordGuesser w = new WordGuesser(t);
        Generator g = new Generator(w);
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("aaaaa");
        wordlist.add("abbbb");
        wordlist.add("bbbbb");
        wordlist.add("abcde");
        wordlist.add("ccccc");
        wordlist.add("cccde");
        wordlist.add("aabcd");
        wordlist.add("abccd");
        wordlist.add("aabbc");
        wordlist.add("abdda");
        g.loadDict("test.txt");
        assertEquals(wordlist.size(), g.getWordlist().size());
        assertEquals(wordlist.get(1), g.getWordlist().get(1));

    }

    @Test
    public void testSetWordlist() {
        Trie t = new Trie();
        t.buildTrie("wiktionary.txt");
        WordGuesser w = new WordGuesser(t);
        Generator g = new Generator(w);
        g.loadDict("test.txt");
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.add("aa");
        wordlist.add("bb");
        g.setWordlist(wordlist);

        assertEquals(wordlist.size(), g.getWordlist().size());
        assertEquals(wordlist.get(1), g.getWordlist().get(1));
    }

}
