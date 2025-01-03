package hash_functions;

import java.math.BigInteger;

public class FNV implements Hash{
    private static final BigInteger INIT64 = new BigInteger("cbf29ce484222325",16);
    /**In decimal, cbf29ce484222325 is equivalent to 14695981039346656037.
      Used as initial number in FNV hash function
     */
    private static final BigInteger PRIME64 = new BigInteger("100000001b3",16);
    // In decimal 100000001b3 is equal to 1099511628211
    private static final BigInteger MOD64 = new BigInteger("2").pow(64);


    @Override
    public long hah(Object o) {
        /**
         * Generates 64 bit hash from byte array with default seed value.
         *
         * @param o Input object whose hash value needs to be calculated
         * @return 64 bit hash of the given input object
         */
        if( o == null){
            return 0;
        }
        else if ( o instanceof Long) return hah(Long.toString((Long) o).getBytes());
        else if ( o instanceof Integer) return hah(Integer.toString((Integer) o).getBytes());
        else if ( o instanceof Double) return hah(Double.toString((Double) o).getBytes());
        else if ( o instanceof Float) return hah(Float.toString((Float) o).getBytes());
        else if ( o instanceof String) return hah(((String) o).getBytes());
        else if( o instanceof byte[]){
            final byte[] data = (byte[]) o;
            return  fnv_64(data);
        }
        return hah(o.toString().getBytes());
    }

    private long fnv_64(byte[] data){
        /**
         * Generates 64 bit hash from byte array with default seed value.
         *
         * @param data byte array to hash
         * @return 64 bit hash of the input byte array
         */

        BigInteger hash = INIT64;
        for(byte b: data){
            hash = hash.multiply(PRIME64).mod(MOD64);
            hash = hash.xor(BigInteger.valueOf((int)b & 0xff));
        }
        return hash.longValue();
    }
}

