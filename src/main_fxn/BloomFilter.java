package main_fxn;

import hash_functions.Hash;
import hash_functions.FNV;
import hash_functions.Murmur;
public class BloomFilter {
    private static final double FALSE_POSITIVE_PROBABILITY = 0.05;
    private boolean bitArray[];
    private int size,hashCount;
    private final Hash hash;

    public BloomFilter(int itemsCount,String hashType ){
        initialize(itemsCount);

        if(hashType.equals("FNV")){
            hash = new FNV();
        }
        else{
            hash = new Murmur();
        }
    }
    private void initialize(int itemCount){
        size = getSize(itemCount);
        hashCount = getHashFunctionsCount(itemCount,size);
        bitArray = new boolean[size];
    }

    private int getSize(int itemCount){
        double size = -(itemCount*Math.log(FALSE_POSITIVE_PROBABILITY))/(Math.log(2)*Math.log(2));
        return (int) size;
    }
    private int getHashFunctionsCount(int itemsCount, int size) {
        double hashCount = (size / itemsCount) * Math.log(2);

        return (int) hashCount;
    }
    public void add(Object item) {
        for (int i = 0; i < hashCount; i++) {
            long hashValue = hash.hah(item);
            hashValue &= Long.MAX_VALUE;
            int position = (int) (hashValue % (long) size);
            bitArray[position] = true;
        }
    }
    public boolean contains(Object item) {
        for (int i = 0; i < hashCount; i++) {
            long hashValue = hash.hah(item);
            hashValue &= Long.MAX_VALUE;
            int position = (int) (hashValue % (long) size);

            if (!bitArray[position]) {
                return false;
            }
        }

        return true;
    }

}
