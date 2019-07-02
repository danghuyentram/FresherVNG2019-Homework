package hashtable;

public class main {
    public static void main(String[] args){
        hash_table hash_table = new hash_table(11,2);


        hash_table.insert("quyen", "6660");
        hash_table.insert("phuc", "78e8e");
        hash_table.insert("quyen", "9995");
        hash_table.insert("phuc", "1c1d2");
        hash_table.insert("kueye", "nvmv");
        hash_table.insert("phuc", "ieoqpe");
        hash_table.insert("quyek", "oeopq");
        hash_table.insert("quyek", "oeopq");
        hash_table.insert("prppr", "mmnlp");



        hash_table.print();
  //      hash_table.delete("a");

//
//        tmp = hash_table.search("a");
//        System.out.println("search "+tmp+" ");
//       hash_table.print();



    }
}
