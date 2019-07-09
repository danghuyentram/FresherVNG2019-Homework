package PredictText;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.Context;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class App {
    static Trie trie = new Trie();
    static BloomFilter bloomFilter = new BloomFilter(1024 * 1024 * 8, 6000);


    static public void readFile() {
        try {
            final File folder = new File("/home/minnie/Desktop/blogs");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            int count = 0;
            for (File inputFile : folder.listFiles()) {
                long startTime = System.nanoTime();

                if (count < 10) {
                    Document doc = dBuilder.parse(inputFile);
                    NodeList nList = doc.getElementsByTagName("post");

                    System.out.println(inputFile.getName());

                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        String[] words_tmp = nNode.getTextContent().split("\\s|,|\\.|-|\\?|\\'s");

                        for (String w : words_tmp) {
                            trie.insert(w);
                            bloomFilter.add(w);
                        }

                    }
                    count++;
                }

                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("totaltime file" + totalTime);

            }


        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    static public void run() {
        char ch;
        do {
            System.out.println("Choose type to function: \n 1. Search Trie \n 2. Search Bloom Filter \n 3. Suggest");
            Scanner scan = new Scanner(System.in);
            SearchContext context;
            int type = scan.nextInt();
            System.out.println("Enter element to search");

            switch (type) {
                case 1:
                    context = SearchContext.getInstance(trie);
                    long startTime = System.nanoTime();
                    System.out.println("Search result : " + context.contains(scan.next()));
                    long endTime = System.nanoTime();
                    long totalTime = endTime - startTime;
                    System.out.println("totaltime " + totalTime);

                    break;
                case 2:
                    context = SearchContext.getInstance(bloomFilter);
                    startTime = System.nanoTime();
                    System.out.println("Search result : " + context.contains(scan.next()));
                    endTime = System.nanoTime();
                    totalTime = endTime - startTime;
                    System.out.println("totaltime " + totalTime);

                    break;
                case 3:
                    System.out.println("Suggest result : " + trie.autoComplete(scan.next()));

            }

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

    public static void main(String[] args) {
        readFile();
        run();
    }


}
