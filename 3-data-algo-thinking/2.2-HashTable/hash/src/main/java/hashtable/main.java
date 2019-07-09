package hashtable;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        hash_table hash_table = new hash_table(11,2);


        hash_table.insert("phuc", "78e8e");
        hash_table.insert("quyen", "9995");
        hash_table.insert("kueye", "nvmv");
        hash_table.insert("quyek", "oeopq");
        hash_table.insert("prppr", "mmnlp");



        hash_table.print();
  //      hash_table.delete("a");

//
        String tmp = hash_table.search("quyen");
        System.out.println("search "+tmp+" ");

        hash_table.delete("quyen");
        hash_table.print();



    }
}
