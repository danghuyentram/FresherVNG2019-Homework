package PredictText;


import java.io.File;
import java.lang.reflect.Array;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import java.util.Arrays;

//public class Trie {
//
//    // Alphabet size (# of symbols)
//    static final int ALPHABET_SIZE = 26;
//
//    // trie node
//    static class TrieNode
//    {
//        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
//
//        // isEndOfWord is true if the node represents
//        // end of a word
//        boolean isEndOfWord;
//
//
//        TrieNode(){
//            isEndOfWord = false;
//            for (int i = 0; i < ALPHABET_SIZE; i++)
//                children[i] = null;
//        }
//    };
//
//    static TrieNode root = new TrieNode();
//
//    // If not present, inserts key into trie
//    // If the key is prefix of trie node,
//    // just marks leaf node
//    static public void insert(String key)
//    {
//        int level;
//        int length = key.length();
//        int index;
//
//        TrieNode pCrawl = root;
//
//        for (level = 0; level < length; level++)
//        {
//
//            if((int)key.charAt(level)>=65 && (int)key.charAt(level)<=90)
//                index = key.charAt(level) - 'A';
//
//            else if ((int)key.charAt(level)>=97 && (int)key.charAt(level)<=122)
//                index = key.charAt(level) - 'a';
//                else break;
//
//            if (pCrawl.children[index] == null)
//                pCrawl.children[index] = new TrieNode();
//
//            pCrawl = pCrawl.children[index];
//
//        }
//
//
//        // mark last node as leaf
//        pCrawl.isEndOfWord = true;
//
//
//    }
//
//    // Returns true if key presents in trie, else false
//    static public boolean search(String key)
//    {
//        int level;
//        int length = key.length();
//        int index;
//        TrieNode pCrawl = root;
//
//        for (level = 0; level < length; level++)
//        {
//            if((int)key.charAt(level)>=65 && (int)key.charAt(level)<=90)
//                index = key.charAt(level) - 'A';
//            else index = key.charAt(level) - 'a';
//
//            if (pCrawl.children[index] == null)
//                return false;
//
//            pCrawl = pCrawl.children[index];
//        }
//
//
//        return (pCrawl != null && pCrawl.isEndOfWord);
//    }
//
//    static public boolean isLastNode(TrieNode root){
//        // if current node has a child: return 0
//        // if all child of root = null: return 1
//
//        for(int i=0;i<ALPHABET_SIZE;i++){
//            if(root.children[i]!=null)
//                return false;
//
//        }
//        return true;
//    }
//
//    // suggest
//
//    static public void suggestion(TrieNode root, String current_prefix){
//
////
////        if(root.isEndOfWord){
////            System.out.println(current_prefix);
////          //  current_prefix=current_prefix.substring(0,current_prefix.length()-1);
////        }
////
////        if(isLastNode(root))
////        {
////            System.out.println("end "+ current_prefix +" "+ current_prefix.substring(0,current_prefix.length()-1));
////            return;
////        }
////
////        for(int i=0;i<ALPHABET_SIZE;i++){
////
////            if(root.children[i]!=null){
////                //current_prefix = current_prefix.substring(0,current_prefix.length()-1);
////                current_prefix = current_prefix +(char)(i+97);
////                suggestion(root.children[i],current_prefix);
////
////            }
////        }
//
//        for(int i=0;i<ALPHABET_SIZE;i++){
//            if(root.children[i]!=null){
//                current_prefix = current_prefix + (char)(i+97);
//
//            }
//        }
//
//    }
//
//    // printSuggestion
//    static public int printSuggestion(TrieNode root,String query){
//        TrieNode pCrawl = root;
//
//        // find query
//        int level;
//        int n = query.length();
//        for(level=0;level < n;level++){
//            int index = query.charAt(level);
//
//            if(index>=65 && index<=90)
//                index = index - 'A';
//            else index = index - 'a';
//
//            // if no string in trie = query -> out
//            if(pCrawl.children[index]==null){
//                return 0;
//            }
//
//            pCrawl = pCrawl.children[index];
//        }
//
//        // if current pCrawl = query and query has no child -> found full query
//        if(pCrawl.isEndOfWord==true && isLastNode(pCrawl)){
//            System.out.println("Find: "+query);
//            return -1;
//        }
//
//        // if current pCrawl = query and query has least one child -> found suggestion for query
//        if(!isLastNode(pCrawl)){
//            String prefix = query;
//            suggestion(pCrawl,prefix);
//            return 1;
//        }
//
//        System.out.println("t");
//        return 0;
//    }
//
//
//    // Driver
//    public static void main(String args[])
//    {
//        // Input keys (use only 'a' through 'z' and lower case)
//        String keys[] = {"abc","ab","abdc","abdec","hexl","hexsfs"};
//
//        String output[] = {"Not present in trie", "Present in trie"};
//
//
//        root = new TrieNode();
//
//        //readFile(root);
//        // Construct trie
//        int i;
//        for (i = 0; i < keys.length ; i++)
//            insert(keys[i]);
//
//       if( search("hexs")==true)
//           System.out.println("found");
//       else System.out.println("not found");
//
//        int comp = printSuggestion(root,"ab");
////        if (comp == -1)
////            System.out.println( "No other strings found with this prefix");
//
//         if (comp == 0)
//            System.out.println("No string found with this prefix");
//
//
//    }
//}



import java.util.*;
import java.util.Map.Entry;



public class Trie implements Dictionary{

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

    public boolean contains(String word){
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
        if(node.terminal==true)
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