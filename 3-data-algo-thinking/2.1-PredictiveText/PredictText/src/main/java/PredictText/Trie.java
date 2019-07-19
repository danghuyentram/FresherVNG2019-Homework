package PredictText;


import java.util.*;
import java.util.Map.Entry;


public class Trie implements Dictionary {

    protected final Map<Character, Trie> children;
    protected String value;
    protected boolean terminal = false;

    public Trie() {
        this(null);
    }

    private Trie(String value) {
        this.value = value;
        children = new HashMap<Character, Trie>();
    }

    protected void add(char c) {
        String val;

        // node is start of new word
        if (this.value == null) {
            val = Character.toString(c);
        } else { // node is continue of word
            // add c to value before of word
            val = this.value + c;

        }

        // add new node child with key c
        children.put(c, new Trie(val));
    }

    public void insert(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Cannot add null to a Trie");
        }
        Trie node = this;

        // for each char in word
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                // node dont have any child with key c -> add new node child
                node.add(c);
            }
            // go to child node with key c
            node = node.children.get(c);
        }

        // mark end of word
        node.terminal = true;
    }

    public boolean contains(String word) {
        Trie node = this;
        // for each char in word
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                // node dont have any child with key c -> not contain
                return false;
            }

            // go to child node with key c
            node = node.children.get(c);
        }

        // end of word and node is terminal of word -> contain
        if (node.terminal == true)
            return true;
        else return false;
    }

    public Collection<String> autoComplete(String prefix) {
        Trie node = this;
        // for each char in prefix
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                // node dont have any childen with key c
                // dont have any word with prefix -> out
                return Collections.emptyList();
            }

            // have a childen with key c -> go to childen
            node = node.children.get(c);
        }
        return node.allPrefixes();
    }

    protected Collection<String> allPrefixes() {
        List<String> results = new ArrayList<String>();

        // node is terminal of word -> add to result
        if (this.terminal) {
            results.add(this.value);
        }

        // node is not terminal of word
        // for each child of node
        for (Entry<Character, Trie> entry : children.entrySet()) {
            Trie child = entry.getValue();

            // find all prefix of child
            Collection<String> childPrefixes = child.allPrefixes();
            results.addAll(childPrefixes);
        }

        return results;
    }
}