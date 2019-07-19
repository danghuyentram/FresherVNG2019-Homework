package PredictText;

import java.util.*;
import java.security.*;
import java.math.*;
import java.nio.*;

/* Class BloomFilter */

// https://www.sanfoundry.com/java-program-implement-bloom-filter/

public class BloomFilter implements Dictionary
{
    private byte[] set;
    private int keySize, setSize, size;
    private MessageDigest md;

    /* Constructor */
    public BloomFilter(int capacity, int k)
    {
        setSize = capacity; // m: length of filter
        set = new byte[setSize]; // array store bit
        keySize = k; // k hash funtion
        size = 0;
        try
        {
            // create message digest by getInstrance() method
            md = MessageDigest.getInstance("MD5"); // message digest: hash value,
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new IllegalArgumentException("Error : MD5 Hash not found");
        }
    }
    /* Function to clear bloom set */
    public void makeEmpty()
    {
        set = new byte[setSize];
        size = 0;
        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new IllegalArgumentException("Error : MD5 Hash not found");
        }
    }
    /* Function to check is empty */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /* Function to get size of objects added */
    public int getSize()
    {
        return size;
    }
    /* Function to get hash - MD5 */
    private int getHash(int i)
    {
        md.reset();
        // cover int to byte
        byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
        // pass a byte array to message digest
        md.update(bytes, 0, bytes.length);

        // get hash value
        return Math.abs(new BigInteger(1, md.digest()).intValue()) % (set.length - 1);
    }

    /* Function to add an object */
    public void add(String word)
    {
        int[] tmpset = getSetArray(word);
        for (int i : tmpset)
            set[i] = 1;
        size++;
    }
    /* Function to check is an object is present */
    //public boolean contains(Object obj)
    public boolean contains(String word)
    {
        int[] tmpset = getSetArray(word);
        for (int i : tmpset)
            if (set[i] != 1)
                return false;

        return true;
    }
    /* Function to get set array for an object */
    private int[] getSetArray(String word)
    {
        int[] tmpset = new int[keySize];
        tmpset[0] = getHash(word.hashCode());
        for (int i = 1; i < keySize; i++) {
            tmpset[i] = (getHash(tmpset[i - 1]));

        }
        return tmpset;
    }
}
