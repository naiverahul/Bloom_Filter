package hash_functions;

public class Murmur  implements Hash{
    @Override
    public long hah(Object o){
        /**
         * Generates 64 bit hash from byte array with default seed value.
         *
         * @param o Input object whose hash value needs to be calculated
         * @return 64 bit hash of the input object
         */
        if( o == null) return 0;
        else if ( o instanceof Long) return hah(Long.toString((Long) o).getBytes());
        else if ( o instanceof Integer) return hah(Integer.toString((Integer) o).getBytes());
        else if ( o instanceof Double) return hah(Double.toString((Double) o).getBytes());
        else if ( o instanceof Float) return hah(Float.toString((Float) o).getBytes());
        else if ( o instanceof String) return hah(((String) o).getBytes());
        else if (o instanceof byte[]){
            final byte[] data = (byte []) o;
            return hash64(data);
        }
        return hah(o.toString().getBytes());
    }
    private long hash64(final byte[] data) {

        return hash64(data, 0xe17a1465);
        //Seed is the initial value used to start computation
        // This value provides good distribution and is a experimented value
    }


    private long hash64(final byte[] data,int seed){
        /**
         * Generates 64 bit hash from byte array of the given length and seed.
         *
         * @param data   byte array to hash
         * @param length length of the array to hash
         * @param seed   initial seed value
         * @return 64 bit hash of the given array
         */
        int length = data.length;
        final long m = 0xc6a4a7935bd1e995L ;
        // Large hexadecimal prime used in murmur for hashing with value 14313749767032793493.
        final int r = 47;
        // The value 47 is chosen to ensure a good distribution of hash values by mixing the bits thoroughly.
        long h = (seed & 0xffffffffl) ^ (length*m);
        int length8 = length/8;
        for (int i = 0; i < length8; i++) { // Loop through each 8-byte block
            final int i8 = i * 8; // Calculate the starting index of the current 8-byte block
            long k = // Extract the i th byte,shift by i*8 bytes and convert to long
                    ((long) data[i8 + 0] & 0xff)
                    + (((long) data[i8 + 1] & 0xff) << 8)
                    + (((long) data[i8 + 2] & 0xff) << 16)
                    + (((long) data[i8 + 3] & 0xff) << 24)
                    + (((long) data[i8 + 4] & 0xff) << 32)
                    + (((long) data[i8 + 5] & 0xff) << 40)
                    + (((long) data[i8 + 6] & 0xff) << 48)
                    + (((long) data[i8 + 7] & 0xff) << 56);

            k *= m; // Multiply k by the constant m
            k ^= k >>> r; // XOR k with itself shifted right by r bits
            k *= m; // Multiply k by the constant m again

            h ^= k; // XOR h with k
            h *= m; // Multiply h by the constant m
        }
        switch (length % 8) {
            // Reverse shifting of bytes i*8
            case 7:
                h ^= (long) (data[(length & ~7) + 6] & 0xff) << 48;
            case 6:
                h ^= (long) (data[(length & ~7) + 5] & 0xff) << 40;
            case 5:
                h ^= (long) (data[(length & ~7) + 4] & 0xff) << 32;
            case 4:
                h ^= (long) (data[(length & ~7) + 3] & 0xff) << 24;
            case 3:
                h ^= (long) (data[(length & ~7) + 2] & 0xff) << 16;
            case 2:
                h ^= (long) (data[(length & ~7) + 1] & 0xff) << 8;
            case 1:
                h ^= (long) (data[length & ~7] & 0xff);
                h *= m;
        }
        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;
        return h;
    }
}
