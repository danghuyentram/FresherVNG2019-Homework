package hashtable;

public class hash_function {
    static final int prime_1 = 151;
    static final int prime_2 =  53;

    public int hash(String s, int a, int m){
        // cover string to a large int
        // reduce the size of the int by mod m

        long hash = 0;
        int len_s = s.length();

        for(int i=0;i<len_s;i++){
            hash+=Math.pow(a,len_s-(i+1))*s.charAt(i);
            hash = hash % m;
        }
        return (int)hash;
    }

    public int get_hash(String s, int num_buckets,int i){
        return 0;
    }
}

