# Predictive text
## Giới thiệu
Tham khảo repo sau.
Viết lại hoàn toàn bằng Java một hash table tương tự.
Hiện thực ít nhất 3 cách giải quyết đụng độ.

## Cài đặt
3 cách giải quyết đụng độ: open addressing
- Linear probing
- Double hashing
- Quandratic probing

### Linear probing
```
If slot hash(x) % S đầy, thử (hash(x) + 1) % S
If (hash(x) + 1) % S cũng đầy, thử (hash(x) + 2) % S
If (hash(x) + 2) % S cũng đầy, thử (hash(x) + 3) % S 
.................................................
```
chênh lệch giữa 2 lần tìm chỗ trống là 1 đơn vị

### Quandratic probing
```

If slot hash(x) % S đầy, thử  we try (hash(x) + 1*1) % S
If (hash(x) + 1*1) % S cũng đầy, thử  (hash(x) + 2*2) % S
If (hash(x) + 2*2) % S cũng đầy, thử   (hash(x) + 3*3) % S
..................................................
```
Ta tìm slot thứ i^2 cho lần lặp thử i

### Double hashing
```
If slot hash(x) % S đầy, thử   we try (hash(x) + 1*hash2(x)) % S
If (hash(x) + 1*hash2(x)) % S cũng đầy, thử  (hash(x) + 2*hash2(x)) % S
If (hash(x) + 2*hash2(x)) % S cũng đầy, thử  (hash(x) + 3*hash2(x)) % S
.................................................
```

- Tạo 1 class hash-function gồm phương thức hash và get-hash
```
public class hash_function {
    static final int prime_1 = 151;
    static final int prime_2 =  53;

    public int hash(String s, int a, int m){
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

```

- 3 class linear_prorbing, quandratic, double_hashing kế thừa và cài đặt lại phương thức get_hash
  - linear_probing
```
public class linear_probing extends hash_function{
    public int get_hash(String s, int num_buckets, int i){
        int hash = hash(s,prime_1,num_buckets);
        return (hash+i)%num_buckets;
    }

}
```

  - quandratic_probing
```
public class quandratic_probing extends hash_function{
    public int get_hash(String s, int num_buckets, int i){
        int hash = hash(s,prime_1,num_buckets);
        return (hash+i*i)%num_buckets;
    }
}
```

  - double_hashing
```
public class double_hashing extends hash_function{
    public int get_hash(String s, int num_buckets, int i){
        int hash1 = hash(s,prime_1,num_buckets);
        int hash2 = hash(s,prime_2,num_buckets);
        return (hash1+(hash2*i))%num_buckets;
    }
}
```

## Chạy và kiểm tra
- Insert vào hash
  
```
        hash_table.insert("phuc", "78e8e");
        hash_table.insert("quyen", "9995");
        hash_table.insert("kueye", "nvmv");
        hash_table.insert("quyek", "oeopq");
        hash_table.insert("prppr", "mmnlp");
```
Hashtable lúc này sẽ gồm:
```
item quyek oeopq
item phuc 78e8e
item quyen 9995
item kueye nvmv
item prppr mmnlp
```

- Search theo key quyen
```
hash_table.search("quyen");
```

Output
```
search 9995 
```
- Delete item quyen
```
hash_table.delete("quyen");
```
Output: hashtable lúc này đã xóa item quyen
```
item quyek oeopq
item phuc 78e8e
item kueye nvmv
item prppr mmnlp
```
