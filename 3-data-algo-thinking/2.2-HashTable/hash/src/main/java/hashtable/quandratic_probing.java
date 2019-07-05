package hashtable;

public class quandratic_probing extends hash_function{
    public int get_hash(String s, int num_buckets, int i){
        int hash = hash(s,prime_1,num_buckets);
        return (hash+i*i)%num_buckets;
    }
}
