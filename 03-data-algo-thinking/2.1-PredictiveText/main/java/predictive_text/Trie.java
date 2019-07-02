package predictive_text;

// https://www.geeksforgeeks.org/auto-complete-feature-using-trie/
// https://www.geeksforgeeks.org/trie-insert-and-search/
import java.io.File;
import java.lang.reflect.Array;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import java.util.Arrays;

public class Trie {

    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // trie node
    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;


        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };

    static TrieNode root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    static void insert(String key)
    {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {

            if((int)key.charAt(level)>=65 && (int)key.charAt(level)<=90)
                index = key.charAt(level) - 'A';

            else if ((int)key.charAt(level)>=97 && (int)key.charAt(level)<=122)
                index = key.charAt(level) - 'a';
                else break;

            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];

        }


        // mark last node as leaf
        pCrawl.isEndOfWord = true;


    }

    // Returns true if key presents in trie, else false
    static boolean search(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            if((int)key.charAt(level)>=65 && (int)key.charAt(level)<=90)
                index = key.charAt(level) - 'A';
            else index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }


        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    static public boolean isLastNode(TrieNode root){
        // if current node has a child: return 0
        // if all child of root = null: return 1

        for(int i=0;i<ALPHABET_SIZE;i++){
            if(root.children[i]!=null)
                return false;

        }
        return true;
    }

    // suggest

    static public void suggestion(TrieNode root, String current_prefix){

//
//        if(root.isEndOfWord){
//            System.out.println(current_prefix);
//          //  current_prefix=current_prefix.substring(0,current_prefix.length()-1);
//        }
//
//        if(isLastNode(root))
//        {
//            System.out.println("end "+ current_prefix +" "+ current_prefix.substring(0,current_prefix.length()-1));
//            return;
//        }
//
//        for(int i=0;i<ALPHABET_SIZE;i++){
//
//            if(root.children[i]!=null){
//                //current_prefix = current_prefix.substring(0,current_prefix.length()-1);
//                current_prefix = current_prefix +(char)(i+97);
//                suggestion(root.children[i],current_prefix);
//
//            }
//        }

        for(int i=0;i<ALPHABET_SIZE;i++){
            if(root.children[i]!=null){
                current_prefix = current_prefix + (char)(i+97);

            }
        }

    }

    // printSuggestion
    static public int printSuggestion(TrieNode root,String query){
        TrieNode pCrawl = root;

        // find query
        int level;
        int n = query.length();
        for(level=0;level < n;level++){
            int index = query.charAt(level);

            if(index>=65 && index<=90)
                index = index - 'A';
            else index = index - 'a';

            // if no string in trie = query -> out
            if(pCrawl.children[index]==null){
                return 0;
            }

            pCrawl = pCrawl.children[index];
        }

        // if current pCrawl = query and query has no child -> found full query
        if(pCrawl.isEndOfWord==true && isLastNode(pCrawl)){
            System.out.println("Find: "+query);
            return -1;
        }

        // if current pCrawl = query and query has least one child -> found suggestion for query
        if(!isLastNode(pCrawl)){
            String prefix = query;
            suggestion(pCrawl,prefix);
            return 1;
        }

        System.out.println("t");
        return 0;
    }

    static public void readFile(TrieNode root){
        try {
            File inputFile = new File("src/main/java/predictive_text/test.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("post");

            System.out.println("----------------------------");
            String[] words;

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                nNode.setTextContent(nNode.getTextContent().trim());
                System.out.println("post: "+nNode.getTextContent());

                String[] words_tmp = nNode.getTextContent().split("\\s|,|\\.|-|\\?|\\'");

                for (String w : words_tmp) {

                       // System.out.println("k"+w);
                        insert(w);

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Driver
    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"abc","ab","abdc","abdec","hexl","hexsfs"};

        String output[] = {"Not present in trie", "Present in trie"};


        root = new TrieNode();

        //readFile(root);
        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            insert(keys[i]);

       if( search("hexs")==true)
           System.out.println("found");
       else System.out.println("not found");

        int comp = printSuggestion(root,"ab");
//        if (comp == -1)
//            System.out.println( "No other strings found with this prefix");

         if (comp == 0)
            System.out.println("No string found with this prefix");


    }
}