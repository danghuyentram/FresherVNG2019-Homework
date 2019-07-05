package hashtable;

public class double_hashing extends hash_function{
    public int get_hash(String s, int num_buckets, int i){
        int hash1 = hash(s,prime_1,num_buckets);
        int hash2 = hash(s,prime_2,num_buckets);
        return (hash1+(hash2*i))%num_buckets;
    }
}
