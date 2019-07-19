package hashtable;

public class hash_table {

    static final hash_item delete_item = new hash_item(null,null);
    static final int base_size = 5;


    int size;
    int count;
    int hash_function_id;
    hash_item[] items ;
    hash_function hash_function;



    public  hash_table(int size,int hash_function_id){
        this.size=size;
        this.count=0;
        items = new hash_item[size];

        if (hash_function_id==0){
            // linear probing
            hash_function = new linear_probing();
        }
        else if(hash_function_id==1){
            // quandratic probing
            hash_function = new quandratic_probing();
        }
        else {
            // double hashing
            hash_function = new double_hashing();
        }

    }


    public int get_hash(String s, int num_buckets, int attempt){
        return hash_function.get_hash(s,num_buckets,attempt);
    }




    public void insert(String key, String value){
        int load = (this.count+1)*100/this.size;
        if(load >70){
            this.resize_up();
        }



        hash_item item = new hash_item(key,value);
        // get index to new hash_item
        int index = get_hash(item.getKey(),this.size,0);

        // get current_item in index
        hash_item cur_item = this.items[index];

        int i=1;

        // current item in index is vailable -> collision
        // try to find other index to new hash_item
        while(cur_item!=null && cur_item!=delete_item){
            index = get_hash(item.getKey(),this.size,i);
            cur_item = this.items[index];
            i++;
        }

        // insert new hash_item to hashtable in index
        this.items[index] = item;
        this.count++;
    }


    public String search(String key){

        int index = get_hash(key,this.size,0);
        hash_item item = this.items[index];
        int i=1;
        while (item !=null){
            if (item!=delete_item){
                // found key value
                if (item.getKey()==key){
                    return item.getValue();
                }
            }

            // collision
            // try to find with other hash key
            index = get_hash(key,this.size,i);
            item = this.items[index];
            i++;

        }
        return null;
    }



    public void delete(String key){
        int load = this.count*100/this.size;
        if(load<10)
            this.resize_down();

        int index = get_hash(key,this.size,0);
        hash_item item = this.items[index];
        int i=1;
        while (item!=null){
            if(item!=delete_item){
                // found item to delete -> delete
                if(item.getKey()==key){
                    this.items[index] = delete_item;
                    break;
                }
            }

            // collision
            // try to find item to delete with other hash key
            index = get_hash(key,this.size,i);
            item = this.items[index];
            i++;
        }
        this.count--;
    }


    public hash_table new_size(int base_size){
        hash_table hash_table = new hash_table(base_size,this.hash_function_id);
        return hash_table;
    }



    public void resize(int base_size){
        hash_table new_hash_table = new_size(base_size);
        for(int i=0;i<this.size;i++){
            hash_item item = this.items[i];
            if(item!=null && item!=delete_item){
                new_hash_table.insert(item.getKey(),item.getValue());
            }
        }

        this.count = new_hash_table.count;

        int tmp_size = this.size;
        this.size = new_hash_table.size;
        new_hash_table.size = tmp_size;

        hash_item[] tmp_items = this.items;
        this.items = new_hash_table.items;
        new_hash_table.items = tmp_items;



    }

    public void  resize_up(){
        int new_size = this.size *2;
        prime prime = new prime();
        int prime_size = prime.next_prime(new_size);
        this.resize(prime_size);
    }

    public void resize_down(){
        int new_size = this.size/2;
        prime prime = new prime();
        int prime_size = prime.next_prime(new_size);
        resize(prime_size);
    }

    public void print(){
        for(int i=0;i<this.size;i++){
            hash_item item = this.items[i];
            if(item!=null && item!=delete_item){
                System.out.println("item "+item.getKey()+" "+item.getValue());
            }
        }
    }



}
